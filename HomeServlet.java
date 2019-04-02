package com.cts.insurance.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.insurance.bo.HomeownerBO;
import com.cts.insurance.bo.LocationBO;
import com.cts.insurance.bo.PolicyBO;
import com.cts.insurance.bo.PropertyBO;
import com.cts.insurance.bo.QuoteBO;
import com.cts.insurance.bo.UserBO;
import com.cts.insurance.model.Homeowner;
import com.cts.insurance.model.Location;
import com.cts.insurance.model.Policy;
import com.cts.insurance.model.Property;
import com.cts.insurance.model.Quote;
import com.cts.insurance.model.User;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> errorList;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/showLogin":
				showLoginPage(request, response);
				break;
			case "/login":
				loginUser(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			case "/showRegistrationPage":
				showRegistrationPage(request, response);
				break;
			case "/registerUser":
				registerUser(request, response);
				break;
			case "/showWelcomePage":
				showWelcomePage(request, response);
				break;
			case "/showEditPage":
				showEditPage(request, response);
				break;
			case "/editUser":
				editUser(request, response);
				break;
			case "/deleteUser":
				deleteUser(request, response);
				break;
			case "/showGetQuote":
				showGetQuote(request, response);
				break;
			case "/getQuote":
				getQuote(request, response);
				break;
			case "/showGetHomeowner":
				showGetHomeowner(request, response);
				break;
			case "/getHomeowner":
				getHomeowner(request, response);
				break;
			case "/showGetProperty":
				showGetProperty(request, response);
				break;
			case "/getProperty":
				getProperty(request, response);
				break;
			case "/showCoverage":
				showCoverage(request, response);
				break;
			case "/calCoverage":
				calCoverage(request, response);
				break;
			case "/showAddPage":
				showAddPage(request, response);
				break;
			case "/showSummary":
				showSummary(request, response);
				break;
			case "/showBuyPolicy":
				showBuyPolicy(request, response);
				break;
			case "/buyPolicy":
				buyPolicy(request, response);
				break;
			case "/showTerm":
				showTerm(request, response);
				break;
			case "/showPolicyPage":
				showPolicyPage(request, response);
				break;
			case "/showQuoteList":
				showQuoteList(request, response);
				break;
			case "/showConfirmPolicy":
				showConfirmPolicy(request, response);
				break;
//			case "/adminPage":
//				showAdminPage(request, response);
//				break;
//			case "/adminLogin":
//				adminLogin(request, response);
//				break;
//			case "/showSearchPage":
//				showSearchPage(request, response);
//				break;
			default:
				showLoginPage(request, response);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/LoginPage.jsp");
		rd.forward(request, response);
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30);

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		UserBO ub = new UserBO();
		User user = ub.getUserByName(userName);
		if (user == null) {
			errorList = new ArrayList<>();
			errorList.add("Invalid Login");
			request.setAttribute("errorList", errorList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp");
			rd.forward(request, response);
		} else {
			if (password.equals(user.getPassword())) {
				session.setAttribute("currentUser", user);
				response.sendRedirect("showWelcomePage");
			} else {
				errorList = new ArrayList<>();
				errorList.add("Invalid Login");
				request.setAttribute("errorList", errorList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp");
				rd.forward(request, response);
			}
		}
	}

	private void showWelcomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		UserBO ub = new UserBO();
		List<User> users = ub.getAllUsers(); // get all user
		request.setAttribute("users", users);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/WelcomePage.jsp");
		rd.forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("showLogin");
	}

	private void showRegistrationPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/RegistrationPage.jsp");
		rd.forward(request, response);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, SQLException {
		String userName = request.getParameter("userName");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String adminRole = request.getParameter("adminRole");

		System.out.println(adminRole);
		if (password1.equals(password2)) {
			String password = password1;
			System.out.println(password);

			User user = new User(userName, password, adminRole);
			UserBO ub = new UserBO();

			int id = ub.registerUser(user);
			System.out.println(id);
			System.out.println("Registerd user. ");
			response.sendRedirect("showLogin");
		} else {
			System.out.println("Password not match. ");
			response.sendRedirect("showRegistrationPage");
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		System.out.println(id);
//		UserBO u_dao = new UserDAO();
//		Boolean removeConfirm = u_dao.removeUser(id);
//		System.out.println(removeConfirm);
//		response.sendRedirect("showWelcomePage");
	}

	private void showEditPage(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, IOException, SQLException, ServletException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		UserDAO u_dao = new UserDAO();
//		User userToUpdate = u_dao.getUserById(id);
//		request.setAttribute("userToUpdate", userToUpdate);
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/Edit.jsp");
//		rd.forward(request, response);
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ClassNotFoundException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		
//		/* The following lines demonstrate an alternative way to retrieve
//		 * parameters from the JSP form using the request.getParameterMap
//		 * method. This method returns a Map of type <String,String[]> so
//		 * we must specify index[0] to retrieve the String inside the value 
//		 * (which is a String array) of each key.*/
//		Map<String, String[]> paramMap = request.getParameterMap();
//		
//		User user = new User(id, 
//				paramMap.get("userName")[0],
//				paramMap.get("password")[0], 
//				paramMap.get("locationCity")[0], 
//				paramMap.get("locationState")[0]);
//		
//		UserDAO u_dao = new UserDAO();
//		System.out.println("User userName: " + user.getUserName());
//		
//		Boolean confirmUpdate = u_dao.updateUser(user);
//		System.out.println(confirmUpdate);
//		response.sendRedirect("showWelcomePage");
	}

	private void showGetQuote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/GetLocationPage.jsp");
		rd.forward(request, response);
	}

	private void getQuote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		int userId = Integer.parseInt(request.getParameter("id"));
		String residenceType = request.getParameter("residenceType");
		String addressLine1 = request.getParameter("address1");
		String addressLine2 = request.getParameter("address2");
		String locationState = request.getParameter("state");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zip");
		String residenceUse = request.getParameter("residenceUse");
		System.out.println(userId);

		Location location = new Location(userId, residenceType, addressLine1, addressLine2, city, locationState,
				zipCode, residenceUse);
		LocationBO lb = new LocationBO();

		int locid = lb.createLocation(location);
		location.setLocationId(locid);

		// System.out.println(l.getLocationId());

		HttpSession session = request.getSession();
		session.setAttribute("quoteLocation", location);

		// System.out.println(location.getLocationId());

		response.sendRedirect("showGetHomeowner");

	}

	private void showGetHomeowner(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/GetHomeownerPage.jsp");
		rd.forward(request, response);
	}

	private void getHomeowner(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

		// Get parameters
		int userId = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int retiredStatus = Integer.parseInt(request.getParameter("retired"));
		String ssn = request.getParameter("ssn");
		String email = request.getParameter("email");

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dob = request.getParameter("dob");
		java.util.Date fd = formatter.parse(dob);
		java.sql.Date sqlDate = new java.sql.Date(fd.getTime());

		System.out.println(userId);
		System.out.println(ssn);
		System.out.println(retiredStatus);
		System.out.println(sqlDate);
		System.out.println(email);

		Homeowner homeowner = new Homeowner(userId, firstName, lastName, sqlDate, retiredStatus, ssn, email);
		HomeownerBO hb = new HomeownerBO();

		Homeowner h = hb.createHomeowner(homeowner);
		// System.out.println(h.getUserId());
		System.out.println("move to property");
		//h.setUserId(userId);

		// System.out.println(l.getLocationId());

		HttpSession session = request.getSession();
		session.setAttribute("homeowner", homeowner);

		response.sendRedirect("showGetProperty");
	}

	private void showGetProperty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/GetPropertyPage.jsp");
		rd.forward(request, response);
	}

	private void getProperty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/Coverage.jsp");
//		rd.forward(request, response);

		HttpSession session = request.getSession();
		Location location = (Location)session.getAttribute("quoteLocation");

		// Get parameters
		int userId = Integer.parseInt(request.getParameter("id"));
		Float marketValue = Float.parseFloat(request.getParameter("marketValue"));
		int yearBuilt = Integer.parseInt(request.getParameter("yearBuilt"));
		int squareFootage = Integer.parseInt(request.getParameter("squareFootage"));
		String dwellingType = request.getParameter("dwellingStyle");
		String roofMaterial = request.getParameter("roofingMaterial");
		String garageType = request.getParameter("garageType");
		int fullBaths = Integer.parseInt(request.getParameter("fullBaths"));
		int halfBaths = Integer.parseInt(request.getParameter("halfBaths"));
		int pool = Integer.parseInt(request.getParameter("swimmingPool"));

		System.out.println(userId);
		System.out.println(location.getLocationId());

//		System.out.println(sqlDate);
//		System.out.println(email);
		PropertyBO pb = new PropertyBO();

		Property property = new Property(location.getLocationId(), marketValue, yearBuilt, squareFootage, dwellingType,
				roofMaterial, garageType, fullBaths, halfBaths, pool);

		Property p = pb.createProperty(property);
		// System.out.println(p.getLocationId());
	//	session.setAttribute("quoteProperty", property);

		response.sendRedirect("calCoverage");

	}

	private void calCoverage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		// GetSession
		HttpSession session = request.getSession();
		Location location = (Location)session.getAttribute("quoteLocation");
		// Homeowner homeowner = (Homeowner)session.getAttribute("homeowner");
//		Property property = (Property) session.getAttribute("property");
		// Declare variables
		int locId = location.getLocationId();
		PropertyBO pb = new PropertyBO();
		Property p = pb.getAllPropertyByLocationId(locId);
		float marketValue = p.getMarketValue();
		double EU = 1000.0;
		double rate = 5.0;
		double yearlyPremium = rate * (marketValue / EU);
		float monthlyPremium = (float) (yearlyPremium / 12);
		String residenceType = location.getResidenceType();
		int square = p.getSquareFootage();
		int yearBuilt = p.getYearBuilt();
		Calendar cal = Calendar.getInstance();

		double constructionCost = 120;
		double homeValue = square * constructionCost;
		int currentYear = cal.get(Calendar.YEAR);
		int homeAge = currentYear - yearBuilt;
		float dwellingCoverage = (float) ((0.5 * marketValue) + homeValue);
		float detachedStructures = (float) (0.1 * dwellingCoverage);
		float personalProperty = (float) (0.6 * dwellingCoverage);
		float addLivingExp = (float) (0.2 * dwellingCoverage);
		float medicalExpenses = (float) 5000.0;
		float deductible = (float) (0.01 * marketValue);

		System.out.println(location.getLocationId());
		System.out.println(marketValue);
		System.out.println("EU " + EU);
		System.out.println("rate " + rate);
		System.out.println("YP " + yearlyPremium);
		System.out.println(monthlyPremium);

		// Condition yearlyPremium by residenceType
		if (residenceType.equals("Single")) {
			yearlyPremium = (yearlyPremium * 0.05) + yearlyPremium;
		} else if (residenceType.equals("Condo") || residenceType.equals("Duplex")
				|| residenceType.equals("Apartment")) {
			yearlyPremium = (yearlyPremium * 0.06) + yearlyPremium;
		} else if (residenceType.equals("Townhouse") || residenceType.equals("Rowhouse")) {
			yearlyPremium = (yearlyPremium * 0.07) + yearlyPremium;
		}

		// Condition homevalue by homeage
		if (homeAge < 5) {
			homeValue = homeValue - (homeValue * 0.1);
		} else if (homeAge > 5 && homeAge < 10) {
			homeValue = homeValue - (homeValue * 0.2);
		} else if (homeAge > 10 && homeAge < 20) {
			homeValue = homeValue - (homeValue * 0.3);
		} else if (homeAge > 20) {
			homeValue = homeValue - (homeValue * 0.5);
		}

		Quote quote = new Quote();
		QuoteBO qb = new QuoteBO();
		quote.setLocationId(locId);
		quote.setMonthlyPremium(monthlyPremium);
		quote.setDwellingCoverage(dwellingCoverage);
		quote.setDetatchedStructures(detachedStructures);
		quote.setPersonalProperty(personalProperty);
		quote.setAddLivingExp(addLivingExp);
		quote.setMedicalExpenses(medicalExpenses);
		quote.setDeductible(deductible);
		
		int qId = qb.createQuote(quote);
		quote.setQuoteId(qId);
		
		System.out.println(quote.getQuoteId());
		//Set quote session
		session.setAttribute("quote", quote);

		response.sendRedirect("showCoverage");
	}

	private void showCoverage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GetSession
		HttpSession session = request.getSession();
		Quote quote = (Quote)session.getAttribute("quote");
		
		int qId = quote.getQuoteId(); 
//		System.out.println("showCoverage Detail");
//		System.out.println(qId);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/Coverage.jsp");
		rd.forward(request, response);
	}
	
	private void showAddPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/Additional.jsp");
		rd.forward(request, response);
	}
	
	private void showSummary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		int qId = Integer.parseInt(request.getParameter("qid"));
		int userId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Quote quote = (Quote)session.getAttribute("quote");
		Location location = (Location)session.getAttribute("quoteLocation"); 
		Homeowner homeowner = (Homeowner)session.getAttribute("homeowner");

		int locId = location.getLocationId();
		System.out.println(locId);
		Property property = new Property();
		PropertyBO pb = new PropertyBO();
		property = pb.getAllPropertyByLocationId(locId);
		
		session.setAttribute("property", property);

						
		System.out.println(locId);
		System.out.println(property.getMarketValue());
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/QuoteSummary.jsp");
		rd.forward(request, response);
	}
	
	private void showBuyPolicy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/BuyPolicy.jsp");
		rd.forward(request, response);
	}
	
	private void buyPolicy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
		// GetSession
		HttpSession session = request.getSession();
		Policy policy = (Policy)session.getAttribute("policy");
		
		int qId = Integer.parseInt(request.getParameter("qid"));
		int userId = Integer.parseInt(request.getParameter("id"));
		
		Policy p = new Policy();
		PolicyBO polb = new PolicyBO();

		List<Policy> policyList;
		// Setup Dates
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String effectiveDate = request.getParameter("startDate");
		java.util.Date fd = formatter.parse(effectiveDate);
		java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
		

		Calendar c = Calendar.getInstance();
		c.setTime(fd);
		c.add(Calendar.DAY_OF_MONTH, 365);

		
		java.util.Date endDate = c.getTime();
		java.sql.Date sqlDate1 = new java.sql.Date(endDate.getTime());
		
		p.setQuoteId(qId);
		p.setUserId(userId);
		p.setEffectiveDate(sqlDate);
		p.setEndDate(sqlDate1);
		p.setTerm(1);
		p.setPolicyStatus("active");

		System.out.println(qId);
		System.out.println(userId);
		System.out.println(sqlDate);
		System.out.println(sqlDate1);

		
		int polId = polb.createPolicy(p);
	//	policy.setPolicyId(polId);
		
		//Set session
		session.setAttribute("policy", policy);
		
		//java.sql.Date sqlDate1 = new java.sql.Date(fd1.getTime());
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/PolicyConfim.jsp");
		rd.forward(request, response);
	}
	
	private void showQuoteList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Quote quote = new Quote();
		QuoteBO qb = new QuoteBO();
		
		List<Quote> quotes = qb.getQuoteByUserId(userId);

		request.setAttribute("quotes", quotes);
		
		for (Quote p1 : quotes) {

			System.out.printf("Quote ID %d: %s\n", p1.getQuoteId(), p1.getMonthlyPremium());
			
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/QuoteList.jsp");
		rd.forward(request, response);
	}
	
	private void showPolicyPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		int userId = Integer.parseInt(request.getParameter("id"));
		Policy p = new Policy();
		PolicyBO polb = new PolicyBO();
		List<Policy> policies = polb.getAllPolicyByUserId(userId);

		request.setAttribute("policies", policies);
		
		for (Policy p1 : policies) {

			System.out.printf("User ID %d: %s\n", p1.getUserId(), p1.getPolicyId());
			
		}
		//System.out.println(userId);
	
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/PolicyList.jsp");
		rd.forward(request, response);
	}
	
	private void showTerm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/Terms.jsp");
		rd.forward(request, response);
	}
	
	private void showConfirmPolicy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/PolicyConfirm.jsp");
		rd.forward(request, response);
	}

	private void showAdminPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/AdminPage.jsp");
		rd.forward(request, response);
	}

	private void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		UserBO ub = new UserBO();
		User user = ub.getUserByName(userName);

		if (user == null) {
			errorList = new ArrayList<>();
			errorList.add("Invalid Login");
			request.setAttribute("errorList", errorList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp");
			rd.forward(request, response);
		} else {
			System.out.println(user.getAdminRole());
			if (password.equals(user.getPassword()) && user.getAdminRole().equals("admin")) {
				session.setAttribute("currentUser", user);
				response.sendRedirect("showSearchPage");
			} else {
				errorList = new ArrayList<>();
				errorList.add("Invalid Login");
				request.setAttribute("errorList", errorList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/AdminPage.jsp");
				rd.forward(request, response);
			}
		}
	}

	private void showSearchPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/SearchPage.jsp");
		rd.forward(request, response);
	}

}
