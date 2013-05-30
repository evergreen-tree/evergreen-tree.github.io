/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava.http.handlers;

import java.io.IOException;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.RuleInfo;
import com.tensegrity.palojava.http.HttpConnection;
import com.tensegrity.palojava.http.builders.InfoBuilderRegistry;
import com.tensegrity.palojava.http.builders.RuleInfoBuilder;
import com.tensegrity.palojava.impl.RuleImpl;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: RuleHandler.java,v 1.16 2007/12/12 16:13:01 ArndHouben Exp $
 */
public class RuleHandler extends HttpHandler {

	//operational prefixes:
	private static final String CREATE_PREFIX = "/rule/create?database=";
	private static final String DELETE_PREFIX = "/rule/destroy?database=";
	private static final String PARSE_RULE_PREFIX = "/rule/parse?database=";
	private static final String LIST_FUNCTIONS_PREFIX = "/rule/functions?";
	private static final String MODIFY_PREFIX = "/rule/modify?database=";
	private static final String INFO_PREFIX = "/rule/info?database=";
	
	//parameter prefixes
	private static final String RULE_PREFIX = "&rule=";
	private static final String DEFINITION_PREFIX = "&definition=";
	private static final String FUNCTIONS_PREFIX = "&functions=";

	
	//--------------------------------------------------------------------------
	// FACTORY
	//
	private static final RuleHandler instance = new RuleHandler();
	static final RuleHandler getInstance(HttpConnection connection) {
		instance.use(connection);
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private final InfoBuilderRegistry builderReg;
	private RuleHandler() {
		builderReg = InfoBuilderRegistry.getInstance();
	}

// 	Parse an enterprise rule
// 	returns  	xml_rule 	string 	XML representation of the enterprise rule.
 	public final String parse(CubeInfo cube, String ruleDefinition, String functions)
			throws IOException {
		DatabaseInfo database = cube.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(PARSE_RULE_PREFIX);
		query.append(database.getId());
		if (functions == null || functions.length() == 0) {
			query.append(CUBE_PREFIX);
			query.append(cube.getId());
		}
		query.append(DEFINITION_PREFIX);
		query.append(encode(ruleDefinition));
		if (functions != null) {
			query.append(FUNCTIONS_PREFIX);
			query.append(encode(functions));
		}		
		String[][] response = request(query.toString());
		return response[0][0];
	}
 	
	public final String listFunctions() throws IOException {
		String[][] response = request(LIST_FUNCTIONS_PREFIX);
		StringBuffer functionStr = new StringBuffer();
		for(int i=0;i<response.length;++i)
			for(int j=0;j<response[i].length;++j)
				functionStr.append(response[i][j]);
		return functionStr.toString(); //response[0][0];		
	}

	public final RuleInfo create(CubeInfo cube, String definition) throws IOException {
		return create(cube,definition, null,false,null);
	}
	
	public final RuleInfo create(CubeInfo cube, String definition,
			 String externalIdentifier, boolean useIt, String comment) throws IOException {
		DatabaseInfo database = cube.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(CREATE_PREFIX);
		query.append(database.getId());
		query.append(CUBE_PREFIX);
		query.append(cube.getId());
		addRuleParameter(query, definition, externalIdentifier,
				useIt, comment);
		String[][] response = request(query.toString());
		RuleInfoBuilder ruleBuilder = builderReg.getRuleBuilder();
		return ruleBuilder.create(cube, response[0]);
	}
	
	public final boolean delete(RuleInfo rule) throws IOException {
		CubeInfo cube = rule.getCube();
		DatabaseInfo database = cube.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(DELETE_PREFIX);
		query.append(database.getId());
		query.append(CUBE_PREFIX);
		query.append(cube.getId());
		query.append(RULE_PREFIX);
		query.append(rule.getId());
		String[][] response = request(query.toString());
		return response[0][0].equals(OK);
	}
	
	public final boolean delete(String ruleId, CubeInfo cube) throws IOException {
		DatabaseInfo database = cube.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(DELETE_PREFIX);
		query.append(database.getId());
		query.append(CUBE_PREFIX);
		query.append(cube.getId());
		query.append(RULE_PREFIX);
		query.append(ruleId);
		String[][] response = request(query.toString());
		return response[0][0].equals(OK);
	}
	
	public final RuleInfo getInfo(CubeInfo cube, String id) throws IOException {
		DatabaseInfo database = cube.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(INFO_PREFIX);
		query.append(database.getId());
		query.append("&cube=");
		query.append(cube.getId());
		query.append("&rule=");
		query.append(id);
		if (id.trim().length() == 0) {
			return null;
		}
		String[][] response = request(query.toString());
		RuleInfoBuilder ruleBuilder = builderReg.getRuleBuilder();
		return ruleBuilder.create(cube, response[0]);
	}

	public final void update(RuleInfo rule, String definition,
			String externalIdentifier, boolean useIt, String comment) throws IOException {
		CubeInfo cube = rule.getCube();
		DatabaseInfo database = cube.getDatabase();
		StringBuffer query = new StringBuffer();
		query.append(MODIFY_PREFIX);
		query.append(database.getId());
		query.append(CUBE_PREFIX);
		query.append(cube.getId());
		query.append(RULE_PREFIX);
		query.append(rule.getId());
		addRuleParameter(query, definition, externalIdentifier,
				useIt, comment);
		String[][] response = request(query.toString());
		RuleInfoBuilder ruleBuilder = builderReg.getRuleBuilder();
		ruleBuilder.update(((RuleImpl) rule), response[0]);
	}
	
	private final void addRuleParameter(StringBuffer query, String definition,
			String externalIdentifier, boolean useIt, String comment) {
		if (definition != null && definition.length() > 0) {
			query.append("&definition=");
			query.append(encode(definition));
//			query.append(definition);
		}
		String functions = getFunctions(definition);
		if (functions != null && functions.length() > 0) {
			query.append("&functions=");
			query.append(encode(functions));
		}
		
		if (externalIdentifier != null && externalIdentifier.length() > 0) {
			query.append("&external_identifier=");
			query.append(encode(externalIdentifier));
		}
		if (comment != null && comment.length() > 0) {
			query.append("&comment=");
			query.append(encode(comment));
		}
		query.append("&use_identifier=");
		query.append(useIt ? "1" : "0");
	}
	
	private final String getFunctions(String definition) {
//		int cutIndex = definition.indexOf("&functions=");
//		if(cutIndex > 0) {
//			String functions = definition.substring(cutIndex+);
//			return functions;
//		}
		return null;
	}
}