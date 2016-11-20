/*
 * @author Imane ZEROUALI
 */
package imane.WebServlet;


public class CountDown {

  static int id = 0;
  String nom;
  String langue;
  String date;

  public CountDown() {
    id++;
    this.nom = "";
    this.langue = "";
    this.date = "";
  }

  public int getid() {
    return id;
  }

  public String getnom() {
    return nom;
  }

  public void setnom(String nom) {
    this.nom = nom ;
  }

  public String getdate() {
    return date;
  }

  public void setdate(String date) {
    this.date = date ;
  }

  public String getlangue() {
    return langue;
  }

  public void setlangue(String langue) {
    this.langue = langue ;
  }
  

}
