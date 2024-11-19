package learning.DSWII.code.business.user.response;

import java.util.ArrayList;
import java.util.List;

import learning.DSWII.code.business.ResponseGeneral;

public class ResponseGetAll extends ResponseGeneral {
    public class Dto {
		public List<Object> listUser;
	}

	public Dto dto;

	public ResponseGetAll() {
		dto = new Dto();

		dto.listUser = new ArrayList<>();
	}
}
