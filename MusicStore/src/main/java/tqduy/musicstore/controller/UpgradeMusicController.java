package tqduy.musicstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tqduy.musicstore.entity.Product;
import tqduy.musicstore.utils.HiberneteUtils;

@Controller
@RequestMapping("/admin/music")
public class UpgradeMusicController {
	@RequestMapping()
	public String viewMusic(ModelMap model, HttpServletRequest request) {
		loadList(model);
		return "music";
	}
	
	@RequestMapping(params="add", method=RequestMethod.POST)
	public String upMusic(ModelMap model, HttpServletRequest request) {
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		HiberneteUtils.insertMusic(product);
		
		loadList(model);
		return "music";
	}
	
	@RequestMapping(params="edit", method=RequestMethod.POST)
	public String editMusic(ModelMap model, HttpServletRequest request) {
		int code = Integer.parseInt(request.getParameter("code"));
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		Product product = new Product(code, name, price);
		HiberneteUtils.editMusic(product);
		
		loadList(model);
		return "music";
	}
	
	@RequestMapping(params="delete", method=RequestMethod.POST)
	public String deleteMusic(ModelMap model, HttpServletRequest request) {
		int code = Integer.parseInt(request.getParameter("code"));
		System.out.println(code);
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		Product product = new Product(code, name, price);
		HiberneteUtils.deleteMusic(product);
		
		loadList(model);
		return "music";
	}
	
	private void loadList(ModelMap model) {
		List<Product> listProduct = HiberneteUtils.getListProduct();
		model.addAttribute("list", listProduct);
	}
}
