package com.pcwk.fire.user.domain;

import com.pcwk.fire.cmn.DTO;

public class UserVO extends DTO {
	private String id; //
    private String nName; //
    private String password; 
    private String pNum; //
    private String name; //
    private String email; //
    private String birth; //
    private String sex; //
    private String tel; //
    private String adminP; //
    private int    score; //
    private int    mList;
    private String regDt; //
    private String regId;
    private String modDt;
    private String modId;
//	private String uId;  // 사용자 아이디
//	private String name; // 이름
//	private String passwd; // 미밀번호
//	
//	private Level  level;//등급: 1 -> BASIC,2 -> SILVER,3 -> GOLD
//	private int    login; //로그인
//	private int    recommend;//추천수
//	private String email;//이메일
//	private String regDt;//등록일
	
	public UserVO() {
		
	}

	public UserVO(String id, String nName, String password, String pNum, String name, String email, String birth,
		String sex, String tel, String adminP, int score, int mList, String regDt, String regId, String modDt,
		String modId) {
	super();
	this.id = id;
	this.nName = nName;
	this.password = password;
	this.pNum = pNum;
	this.name = name;
	this.email = email;
	this.birth = birth;
	this.sex = sex;
	this.tel = tel;
	this.adminP = adminP;
	this.score = score;
	this.mList = mList;
	this.regDt = regDt;
	this.regId = regId;
	this.modDt = modDt;
	this.modId = modId;
}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdminP() {
		return adminP;
	}

	public void setAdminP(String adminP) {
		this.adminP = adminP;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getmList() {
		return mList;
	}

	public void setmList(int mList) {
		this.mList = mList;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", nName=" + nName + ", password=" + password + ", pNum=" + pNum + ", name=" + name
				+ ", email=" + email + ", birth=" + birth + ", sex=" + sex + ", tel=" + tel + ", adminP=" + adminP
				+ ", score=" + score + ", mList=" + mList + ", regDt=" + regDt + ", regId=" + regId + ", modDt=" + modDt
				+ ", modId=" + modId + ", toString()=" + super.toString() + "]";
	}

}
