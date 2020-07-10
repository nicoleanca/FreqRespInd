package project.nicole.freqrespind.controller;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import project.nicole.freqrespind.data.ConductivityAndPermittivity;
import project.nicole.freqrespind.data.DebyeParams;

import java.util.HashMap;
import java.util.Map;

@Component
@RestController
public class DebyeController {

	@GetMapping("/getParams/")
	@ResponseBody
	public JSONObject GetParams(@RequestParam String id) throws Exception{
		Long l_id = Long.parseLong(id);
		DebyeParams debyeParams = new DebyeParams();
		debyeParams.readDebye(l_id);
		Map<String, Object> map = new HashMap<>();
		map.put("conduct_sg_0", debyeParams.getConduct_sg_0());
		map.put("e_stat", debyeParams.getE_stat());
		map.put("e_inf", debyeParams.getE_inf());
		map.put("tau", debyeParams.getTau());
		JSONObject json = new JSONObject();
		json.putAll(map);
		return json;
	}

	@GetMapping("/calculateMatrix/")
	@ResponseBody
	public JSONObject calculateMatrix(@RequestParam String conduct_sg_0, @RequestParam String e_inf,
											   @RequestParam String e_stat, @RequestParam String tau) {
		double d_conduct_sg_0 = Double.parseDouble(conduct_sg_0);
		double d_e_inf = Double.parseDouble(e_inf);
		double d_e_stat = Double.parseDouble(e_stat);
		double d_tau = Double.parseDouble(tau);
		DebyeParams debyeparam = new DebyeParams();
	    debyeparam.setConduct_sg_0(d_conduct_sg_0);
		debyeparam.setE_inf(d_e_inf);
	    debyeparam.setE_stat(d_e_stat);
		debyeparam.setTau(d_tau);
		ConductivityAndPermittivity connectivityAndPermittivity = debyeparam.chartData();
		Map<String, Object> map = new HashMap<>();
		map.put("cond", connectivityAndPermittivity.getConductivity());
		map.put("perm", connectivityAndPermittivity.getPermittivity());
		JSONObject json = new JSONObject();
		json.putAll(map);
		return json;
	}
}
