package harsh.rane.model;

public class Element {
	private String element_id;
	private String element_name;
	private String element_ctgy_name;
	private String effective_date;
	private String element_skey;
	
	public String getElement_id() {
		return element_id;
	}
	public void setElement_id(String element_id) {
		this.element_id = element_id;
	}
	public String getElement_name() {
		return element_name;
	}
	public void setElement_name(String element_name) {
		this.element_name = element_name;
	}
	public String getElement_ctgy_name() {
		return element_ctgy_name;
	}
	public void setElement_ctgy_name(String element_ctgy_name) {
		this.element_ctgy_name = element_ctgy_name;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	public String getElement_skey() {
		return element_skey;
	}
	public void setElement_skey(String element_skey) {
		this.element_skey = element_skey;
	}
	@Override
	public String toString() {
		return "Element [element_id=" + element_id + ", element_name=" + element_name + ", element_ctgy_name="
				+ element_ctgy_name + ", effective_date=" + effective_date + ", element_skey=" + element_skey + "]";
	}
}
