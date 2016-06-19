package br.unibh.seguros.controle;

import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean(name = "relatoriosmb")
@ViewScoped
public class ControleRelatorios {

	private Connection conexao = null;

	public void geraRelatorio(String relatorio, String opcao) {
		URL arquivo = getClass().getResource("/relatorios/" + relatorio + ".jasper");
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);
			Context initContext = new InitialContext();
			DataSource ds = (javax.sql.DataSource) initContext.lookup("java:jboss/datasources/seguros");
			conexao = (java.sql.Connection) ds.getConnection();
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conexao);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			ec.responseReset();
			
			if (opcao.equals("inline")) {
				ec.setResponseHeader("Content-Disposition", "inline; filename=" + relatorio + ".pdf");
			} else {
				ec.setResponseHeader("Content-Disposition", "attachment; filename=" + relatorio + ".pdf");
			}
			
			ec.setResponseContentType("application/pdf");
			ec.setResponseContentLength(bytes.length);
			
			OutputStream ouputStream = ec.getResponseOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			
			fc.responseComplete();
			fc.renderResponse();
			System.out.println("gerou");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexao != null)
				try {
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			conexao = null;
		}
	}
}
