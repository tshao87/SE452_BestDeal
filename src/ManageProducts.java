import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ManageProducts")

public class ManageProducts extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		boolean printErrorMsg = false;

		if (request.getParameter("ManageProducts") != null) {
			if (request.getParameter("productIdRadio") != null) {
				printErrorMsg = false;
				if (request.getParameter("ManageProducts").equals("Update")) {
					String key = request.getParameter("productIdRadio");

					String name = request.getParameter("productName" + key);
					double price = Double.parseDouble(request.getParameter("productPrice" + key));
					String image = request.getParameter("productImage" + key);
					String retailer = request.getParameter("productRetailer" + key);
					String condition = request.getParameter("productCondotion" + key);
					double discount = Double.parseDouble(request.getParameter("productDiscount" + key));

					if (key.contains("phone")) {
						SmartPhone sp = new SmartPhone(key, name, price, image, retailer, condition, discount);
						SaxParserDataStore.smartphones.put(key, sp);
					} else if (key.contains("laptop")) {
						Laptop lp = new Laptop(key, name, price, image, retailer, condition, discount);
						SaxParserDataStore.laptops.put(key, lp);
					} else if (key.contains("tablet")) {
						Tablet tb = new Tablet(key, name, price, image, retailer, condition, discount);
						SaxParserDataStore.tablets.put(key, tb);
					} else if (key.contains("tv")) {
						TV tv = new TV(key, name, price, image, retailer, condition, discount);
						SaxParserDataStore.tvs.put(key, tv);
					}
				} else if (request.getParameter("ManageProducts").equals("Delete")) {
					String key = request.getParameter("productIdRadio");

					if (key.contains("phone")) {
						SaxParserDataStore.smartphones.remove(key);
					} else if (key.contains("laptop")) {
						SaxParserDataStore.laptops.remove(key);
					} else if (key.contains("tablet")) {
						SaxParserDataStore.tablets.remove(key);
					} else if (key.contains("tv")) {
						SaxParserDataStore.tvs.remove(key);
					}
				}
			} else {
				printErrorMsg = true;
			}
		}

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ManageProducts' action='ManageProducts' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Manage Products</a>");
		pw.print("</h2><div class='entry'>");
		if (printErrorMsg) {
			pw.print("<h4 style='color:red'>Please select a product by using radio button</h4>");
		}
		pw.print("<table  class='gridtable'>");
		pw.print("<tr><td></td>");
		pw.print("<td>Id:</td>");
		pw.print("<td>Name:</td>");
		pw.print("<td>Price:</td>");
		pw.print("<td>Image:</td>");
		pw.print("<td>Retailer:</td>");
		pw.print("<td>Condition:</td>");
		pw.print("<td>Discount:</td>");
		pw.print("<td>Operation:</td></tr>");

		if (utility.getSmartPhones() != null && utility.getSmartPhones().size() != 0) {
			HashMap<String, SmartPhone> hmsp = utility.getSmartPhones();

			for (String key : hmsp.keySet()) {
				SmartPhone sp = hmsp.get(key);
				pw.print("<tr>");
				pw.print("<td><input type='radio' name='productIdRadio' value='" + key + "'></td>");
				pw.print("<td><input type='text' size='4' name='productId" + sp.getId() + "' value='" + sp.getId()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productName" + sp.getId() + "' value='" + sp.getName()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productPrice" + sp.getId() + "' value='" + sp.getPrice()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productImage" + sp.getId() + "' value='" + sp.getImage()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productRetailer" + sp.getId() + "' value='"
						+ sp.getRetailer() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productCondotion" + sp.getId() + "' value='"
						+ sp.getCondition() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productDiscount" + sp.getId() + "' value='"
						+ sp.getDiscount() + "'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Update' class='btnbuy'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Delete' class='btnbuy'></td>");
				pw.print("</tr>");
			}

			HashMap<String, Laptop> hmlp = utility.getLaptops();

			for (String key : hmlp.keySet()) {
				Laptop lp = hmlp.get(key);
				pw.print("<tr>");
				pw.print("<td><input type='radio' name='productIdRadio' value='" + key + "'></td>");
				pw.print("<td><input type='text' size='4' name='productId" + lp.getId() + "' value='" + lp.getId()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productName" + lp.getId() + "' value='" + lp.getName()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productPrice" + lp.getId() + "' value='" + lp.getPrice()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productImage" + lp.getId() + "' value='" + lp.getImage()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productRetailer" + lp.getId() + "' value='"
						+ lp.getRetailer() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productCondotion" + lp.getId() + "' value='"
						+ lp.getCondition() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productDiscount" + lp.getId() + "' value='"
						+ lp.getDiscount() + "'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Update' class='btnbuy'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Delete' class='btnbuy'></td>");
				pw.print("</tr>");
			}

			HashMap<String, Tablet> hmtb = utility.getTablets();

			for (String key : hmtb.keySet()) {
				Tablet tb = hmtb.get(key);
				pw.print("<tr>");
				pw.print("<td><input type='radio' name='productIdRadio' value='" + key + "'></td>");
				pw.print("<td><input type='text' size='4' name='productId" + tb.getId() + "' value='" + tb.getId()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productName" + tb.getId() + "' value='" + tb.getName()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productPrice" + tb.getId() + "' value='" + tb.getPrice()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productImage" + tb.getId() + "' value='" + tb.getImage()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productRetailer" + tb.getId() + "' value='"
						+ tb.getRetailer() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productCondotion" + tb.getId() + "' value='"
						+ tb.getCondition() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productDiscount" + tb.getId() + "' value='"
						+ tb.getDiscount() + "'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Update' class='btnbuy'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Delete' class='btnbuy'></td>");
				pw.print("</tr>");
			}

			HashMap<String, TV> hmtv = utility.getTVs();

			for (String key : hmtv.keySet()) {
				TV tv = hmtv.get(key);
				pw.print("<tr>");
				pw.print("<td><input type='radio' name='productIdRadio' value='" + key + "'></td>");
				pw.print("<td><input type='text' size='4' name='productId" + tv.getId() + "' value='" + tv.getId()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productName" + tv.getId() + "' value='" + tv.getName()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productPrice" + tv.getId() + "' value='" + tv.getPrice()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productImage" + tv.getId() + "' value='" + tv.getImage()
						+ "'></td>");
				pw.print("<td><input type='text' size='4' name='productRetailer" + tv.getId() + "' value='"
						+ tv.getRetailer() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productCondotion" + tv.getId() + "' value='"
						+ tv.getCondition() + "'></td>");
				pw.print("<td><input type='text' size='4' name='productDiscount" + tv.getId() + "' value='"
						+ tv.getDiscount() + "'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Update' class='btnbuy'></td>");
				pw.print("<td><input type='submit' name='ManageProducts' value='Delete' class='btnbuy'></td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
		}
	}
}
