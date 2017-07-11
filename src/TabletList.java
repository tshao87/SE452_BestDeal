import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TabletList")

public class TabletList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Tablet> hm = new HashMap<String, Tablet>();

		if (CategoryName == null) {
			hm.putAll(SaxParserDataStore.tablets);
			name = "";
		} else {
			if (CategoryName.equals("apple")) {
				for (Map.Entry<String, Tablet> entry : SaxParserDataStore.tablets.entrySet()) {
					if (entry.getValue().getRetailer().equals("Apple")) {
						hm.put(entry.getValue().getId(), entry.getValue());
					}
				}
				name = "Apple";
			} else if (CategoryName.equals("microsoft")) {
				for (Map.Entry<String, Tablet> entry : SaxParserDataStore.tablets.entrySet()) {
					if (entry.getValue().getRetailer().equals("Microsoft")) {
						hm.put(entry.getValue().getId(), entry.getValue());
					}
				}
				name = "Microsoft";
			} else if (CategoryName.equals("samsung")) {
				for (Map.Entry<String, Tablet> entry : SaxParserDataStore.tablets.entrySet()) {
					if (entry.getValue().getRetailer().equals("Samsung")) {
						hm.put(entry.getValue().getId(), entry.getValue());
					}
				}
				name = "Samsung";
			}
		}

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Tablets</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Tablet> entry : hm.entrySet()) {
			Tablet tablet = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + tablet.getName() + "</h3>");
			pw.print("<strong><del>$" + tablet.getPrice() + "</del> <font color=\"red\">$"
					+ Math.round(tablet.getPrice() * (100 - tablet.getDiscount())) / 100.0 + "</font></strong><ul>");
			pw.print("<li id='item'><img src='images/tablets/" + tablet.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" + "<input type='hidden' name='name' value='"
					+ entry.getKey() + "'>" + "<input type='hidden' name='type' value='tablet'>"
					+ "<input type='hidden' name='maker' value='" + CategoryName + "'>"
					+ "<input type='hidden' name='access' value=''>"
					+ "<input type='submit' class='btnbuy' name='BuyBtn' value='Buy Now'>"
					+ "<input type='submit' class='btnbuy' name='BuyBtn' value='Buy with 1yr Care $14.99'>"
					+ "<input type='submit' class='btnbuy' name='BuyBtn' value='Buy with 2yr Care $28.99'>"
					+ "<input type='submit' class='btnbuy' name='BuyBtn' value='Buy with 3yr Care $43.99'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
