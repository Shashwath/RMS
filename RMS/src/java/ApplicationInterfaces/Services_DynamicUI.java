/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationInterfaces;

import javax.faces.component.html.HtmlPanelGrid;
import org.primefaces.component.menubar.Menubar;

/**
 *
 * @author Shashwat
 */
public interface Services_DynamicUI {

public Menubar getAcessibilityBar(int empid,int grpid,int cmpid);
public HtmlPanelGrid createFormElements(int empid,int grpid,int cmpid,int entityid);
public HtmlPanelGrid editFormElements(int empid,int grpid,int cmpid,int entityid,int recordid);

}