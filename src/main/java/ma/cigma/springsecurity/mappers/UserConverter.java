package ma.cigma.springsecurity.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.springsecurity.dto.UserVo;
import ma.cigma.springsecurity.entities.User;

public class UserConverter {
	public static UserVo toVo(User bo) {
		if (bo == null)
			return null;
		UserVo vo = new UserVo();
		vo.setId(bo.getId());
		vo.setUsername(bo.getUsername());
		vo.setPassword(bo.getPassword());
		vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
		return vo;
	}

	public static User toBo(UserVo vo) {
		if (vo == null)
			return null;
		User bo = new User();
		if (vo.getId() != null)
			bo.setId(vo.getId());
		bo.setUsername(vo.getUsername());
		bo.setPassword(vo.getPassword());
		bo.setRoles(RoleConverter.toBoList(vo.getRoles()));
		return bo;
	}

	public static List<UserVo> toVoList(List<User> boList) {
		if (boList == null || boList.isEmpty())
			return null;
		List<UserVo> voList = new ArrayList<>();
		for (User user : boList) {
			voList.add(toVo(user));
		}
		return voList;
	}

	public static List<User> toBoList(List<UserVo> voList) {
		if (voList == null || voList.isEmpty())
			return null;
		List<User> boList = new ArrayList<>();
		for (UserVo userVo : voList) {
			boList.add(toBo(userVo));
		}
		return boList;
	}
}
