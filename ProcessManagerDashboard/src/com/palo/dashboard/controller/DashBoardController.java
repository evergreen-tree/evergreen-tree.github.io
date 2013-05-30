package com.palo.dashboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palo.dashboard.po.ProcessMonitor;
import com.palo.dashboard.po.Result;
import com.palo.dashboard.util.Constants;
import com.palo.dashboard.util.ProcessUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashBoardController {
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String dashbord(HttpServletRequest request) {
		return "dashboard";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/processMonitor")
	public String processMonitor(HttpServletRequest request) {
		return "monitorProcess";
	}

	@RequestMapping(value = "/checkStatus")
	@ResponseBody
	public List<ProcessMonitor> checkStatus(HttpServletRequest request) {
		List<ProcessMonitor> processResultList = new ArrayList<ProcessMonitor>();
		String processName1 = request.getParameter("process1");
		String processName2 = request.getParameter("process2");
		String processName3 = request.getParameter("process3");
		String processName4 = request.getParameter("process4");
		ProcessMonitor processMonitor1 = createProcessMonitorByName(processName1);
		ProcessMonitor processMonitor2 = createProcessMonitorByName(processName2);
		ProcessMonitor processMonitor3 = createProcessMonitorByName(processName3);
		ProcessMonitor processMonitor4 = createProcessMonitorByName(processName4);
		processResultList.add(processMonitor1);
		processResultList.add(processMonitor2);
		processResultList.add(processMonitor3);
		processResultList.add(processMonitor4);
		return processResultList;
	}

	/**
	 * will call the process method to find out whether is running
	 * 
	 * @param processName
	 * @return
	 */
	private ProcessMonitor createProcessMonitorByName(String processName) {
		ProcessMonitor processMonitor = new ProcessMonitor();
		processMonitor.setProcessName(processName);
		boolean isRunning = ProcessUtil.isProcessRunning(processName);
		if (isRunning) {
			processMonitor.setStatus("running");
		} else {
			processMonitor.setStatus("stopped");
		}
		return processMonitor;
	}

	@RequestMapping(value = "/startTomcat")
	@ResponseBody
	public Result startTomcat(HttpServletRequest request) {
		String filePath = Constants.tomcat_location;
		Result result = ProcessUtil.startProcess(filePath);
		result.setProcessName("tomcat");
		return result;
	}

	@RequestMapping(value = "/startPalo")
	@ResponseBody
	public Result startPalo(HttpServletRequest request) {
		String filePath = Constants.olap_location;
		Result result = ProcessUtil.startProcess(filePath);
		result.setProcessName("palo");
		return result;
	}

	@RequestMapping(value = "/startHttpd")
	@ResponseBody
	public Result startHttpd(HttpServletRequest request) {
		System.out.println("start httpd start!");
		String filePath = Constants.httpd_location;
		Result result = ProcessUtil.startProcess(filePath);
		result.setProcessName("httpd");
		System.out.println("start httpd end!");
		return result;
	}

	@RequestMapping(value = "/startCore")
	@ResponseBody
	public Result startCore(HttpServletRequest request) {
		System.out.println("starting core");
		String filePath = Constants.core_location;
		Result result = ProcessUtil.startProcess(filePath);
		result.setProcessName("core");
		System.out.println("end starting core");
		return result;
	}

	@RequestMapping(value = "/stopTomcat")
	@ResponseBody
	public Result stopTomcat(HttpServletRequest request) {
		/* String processName = request.getParameter("processName"); */
		Result result = ProcessUtil.shutdownProcess("javaw.exe");
		result.setProcessName("tomcat");
		return result;
	}

	@RequestMapping(value = "/stopPalo")
	@ResponseBody
	public Result stopPalo(HttpServletRequest request) {
		/* String processName = request.getParameter("processName"); */
		Result result = ProcessUtil.shutdownProcess("palo.exe");
		result.setProcessName("palo");
		return result;
	}

	@RequestMapping(value = "/stopHttpd")
	@ResponseBody
	public Result stopProcessC(HttpServletRequest request) {
		/* String processName = request.getParameter("processName"); */
		Result result = ProcessUtil.shutdownProcess("httpd.exe");
		result.setProcessName("httpd");
		return result;
	}

	@RequestMapping(value = "/stopCore")
	@ResponseBody
	public Result stopProcessD(HttpServletRequest request) {
		/* String processName = request.getParameter("processName"); */
		Result result = ProcessUtil.shutdownProcess("core.exe");
		result.setProcessName("core");
		return result;
	}
}