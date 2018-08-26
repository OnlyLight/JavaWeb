package tqduy.musicstore.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tqduy.musicstore.entity.Product;
import tqduy.musicstore.entity.Account;
import tqduy.musicstore.utils.HiberneteUtils;

@Controller
public class TrangChuController {
	
	@RequestMapping("/")
	public String viewTrangChu() {
		return "index";
	}
	
	@RequestMapping("/product-list")
	public String viewProduct(ModelMap model) {
		List<Product> listProduct = HiberneteUtils.getListProduct();
		model.addAttribute("list", listProduct);
		return "product-list";
	}
	
	@RequestMapping("/admin")
	public String viewAdmin() {
		return "admin";
	}
	
	@RequestMapping("/buyProduct")
	public String listProduct(@RequestParam(value = "code", defaultValue = "") String code, ModelMap model) {
		List<Product> list = null;
		if(code != null && code.length() > 0) {
			list = HiberneteUtils.findProduct(code);
		}
		model.addAttribute("listProductBuy", list);
		return "cart";
	}
	
	@RequestMapping("/login")
	public String checkLogin(HttpServletRequest request) throws SQLException {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Account user = new Account();
		user.setUserName(userName);
		user.setPassWord(passWord);
		if(!HiberneteUtils.checkUser(user)) {
			return "login";
		}
		return "redirect:/index";
	}
}
