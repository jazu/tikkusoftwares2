package projekti.projhallinta;

public class PStatus {
	int projektiID;
	int statusID;
	String status;
	
	public PStatus(int projektiID, int statusID, String status){
		this.projektiID = projektiID;
		this.statusID = statusID;
		this.status = status;
	}

	public int getProjektiID() {
		return projektiID;
	}

	public void setProjektiID(int projektiID) {
		this.projektiID = projektiID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PStatus [projektiID=" + projektiID + ", statusID=" + statusID
				+ ", status=" + status + "]";
	}

}
