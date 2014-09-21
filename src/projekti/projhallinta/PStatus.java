package projekti.projhallinta;

/**
 * The PStatus object is used to store individual project status objects used in database.
 * @author s1200508
 *
 */

public class PStatus {
	int projektiID;
	int statusID;
	String status;
	/**
	 * Creates a new PStatus object with the info given as parameters.
	 * @param projektiID
	 * @param statusID
	 * @param status
	 */
	
	public PStatus(int projektiID, int statusID, String status){
		this.projektiID = projektiID;
		this.statusID = statusID;
		this.status = status;
	}
	
	/**
	 * Returns the ID of the project the PStatus object is representing
	 * @return project ID
	 */

	public int getProjektiID() {
		return projektiID;
	}
	/**
	 * Sets the project ID of this PStatus object
	 * @param projektiID
	 */

	public void setProjektiID(int projektiID) {
		this.projektiID = projektiID;
	}
	/**
	 * Returns the ID of the PStatus object
	 * @return Status ID
	 */

	public int getStatusID() {
		return statusID;
	}
	/**
	 * Sets the Status ID of the Pstatus object.
	 * @param statusID
	 */

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	/**
	 * Returns the Status text of the PStatus object
	 * @return Status text
	 */

	public String getStatus() {
		return status;
	}
	/**
	 * Sets the Status text of the PStatus object
	 * @param status
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PStatus [projektiID=" + projektiID + ", statusID=" + statusID
				+ ", status=" + status + "]";
	}

}
