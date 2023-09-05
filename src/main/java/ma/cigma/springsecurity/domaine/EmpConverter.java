package ma.cigma.springsecurity.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.springsecurity.service.model.Emp;

public class EmpConverter {
	public static EmpVo toVo(Emp bo) {
		if (bo == null || bo.getId() ==null)
			return null;
		EmpVo vo = new EmpVo();
		vo.setId(bo.getId());
		vo.setName(bo.getName2());
		vo.setSalary(bo.getSalary());
		vo.setFonction(bo.getFonction());
		return vo;
	}
	public static Emp toBo(EmpVo vo) {
		Emp bo = new Emp();
		bo.setId(vo.getId());
		bo.setName2(vo.getName());
		bo.setSalary(vo.getSalary());
		bo.setFonction(vo.getFonction());
		return bo;
	}
	public static List<EmpVo> toListVo(List<Emp> listBo) {
		List<EmpVo> listVo = new ArrayList<>();
		for (Emp emp : listBo) {
			listVo.add(toVo(emp));
		}
		return listVo;
	}
}
