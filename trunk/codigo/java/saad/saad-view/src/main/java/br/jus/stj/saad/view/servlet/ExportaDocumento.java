package br.jus.stj.saad.view.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Servlet implementation class ExportaDocumento
 */
public class ExportaDocumento extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_BUFFER_SIZE = 4096;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportaDocumento() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.reset();

		String pathDoc = request.getParameter("pathDoc");

		File file = new File(pathDoc);

		setaConfiguracaoResponse(response, file);

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {

			input = new BufferedInputStream(new FileInputStream(file),
					DEFAULT_BUFFER_SIZE);
			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);

			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;

			while ((length = input.read(buffer)) > 0) {

				output.write(buffer, 0, length);

			}

		} catch (Exception e) {

			try {

				ByteArrayOutputStream baos = obterPdfErroLeitura();

				response.setContentLength(baos.size());

				output = new BufferedOutputStream(response.getOutputStream(),
						DEFAULT_BUFFER_SIZE);

				baos.writeTo(output);

			} catch (DocumentException ex) {

				throw new IOException(ex.getMessage());

			}

		} finally {

			try {

				output.close();
				input.close();

			} catch (Exception e) {
			}

		}

	}
	
	private void setaConfiguracaoResponse(HttpServletResponse response, File file) {
		
		response.setContentType("application/pdf");
		response.setContentLength((int) file.length());
		
		response.setHeader("Content-disposition", "inline; filename=\"" + file.getName() + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Accept-Ranges", "bytes");
		
	}
	
	private ByteArrayOutputStream obterPdfErroLeitura() throws DocumentException {
		
        String text = "Não foi possível encontrar o documento.";
     
        Document document = new Document();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        PdfWriter.getInstance(document, baos);
        
        document.open();
        
        Paragraph paragraph = new Paragraph(text);
        paragraph.setAlignment(Element.ALIGN_MIDDLE);
        document.add(new Paragraph(text));
        
        document.close();
        
        return baos;
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}