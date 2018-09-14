package com.teerawat.registration.model;

import lombok.Data;

@Data
public class RegisterResMsg {
	
	public enum ResponseBody{
		Success(0,"Process Successful"),
		Failed(1,"Process Failed"),
		Reject(2,"Reject with no conditions"),
		ExistUsername(3, "Username is already exist"),
		CannotClassify(4, "Cannot Classify user because salary less than 15,000"),
		AuthenticationFail(5, "Authentication failed")
		;
		int code;
		String description;
		ResponseBody(int code, String description) {this.code = code;this.description=description;}
		
		public ResponseBody getBodyByRegisterResMsg(RegisterResMsg res) {
			switch (res.getCode()) {
				case 0:return ResponseBody.Success;
				case 1:return ResponseBody.Failed;
				case 2:return ResponseBody.Reject;
				case 3:return ResponseBody.ExistUsername;
				case 4:return ResponseBody.CannotClassify;
				case 5:return ResponseBody.AuthenticationFail;
				default:return ResponseBody.Failed;
			}
		}
		public RegisterResMsg getRegisterResMsg() {
			RegisterResMsg res = new RegisterResMsg();
			res.setCode(this.code);
			res.setDescription(this.description);
			return res;
		}
	}
	
	private int code;
	private String description;
	private String authToken;
}
