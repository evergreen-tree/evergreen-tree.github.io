Please read it carefully before you deploy your project

1. deploy under tomcat6 
2. visit http://localhost:8080/HighCharts
	you will see a hello page.
3. visit 
	http://localhost:8080/HighCharts/chart/line
	you will see the line chart
	http://localhost:8080/HighCharts/database
	
4. check the jsp page under WEB-INF/views/
					
5. check the sample code of line chart as below: 
	src/jdu/sample/line   //controller
	src/jdu/sample/option   //entity which will turn into json object


URLs:
1. dashboard: 	http://localhost:8080/HighCharts/dashboard
2. line: 		http://localhost:8080/HighCharts/chart/line
2. column: 		http://localhost:8080/HighCharts/chart/column
2. pie: 		http://localhost:8080/HighCharts/chart/pie
	
	
TIPS FOR DEV:
1, Since we have source code of highchart lib and jquery lib, in DEV stage we use source version, for PROD, we can switch to compressed version.
2, Please try hard to commit codes with comments.	
3, Please use HighCharts version 2.3.5 