package my.webapp.action;

import com.google.gson.JsonObject;
import my.Constants;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;

public class FileUpload extends BasePage implements Serializable {
    private static final long serialVersionUID = 6932775516007291334L;
    private UploadedFile file;
    private String name;

    public String upload() throws IOException, ServletException {
        HttpServletRequest request = getRequest();

        // write the file to the filesystem
        // the directory to upload to
        String uploadDir = getFacesContext().getExternalContext().getRealPath("/resources");
        // The following seems to happen when running jetty:run
        if (uploadDir == null) {
            uploadDir = new File("src/main/webapp/resources").getAbsolutePath();
        }
        uploadDir += "/" + request.getRemoteUser() + "/";

        // Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        Part selectedFile = request.getPart("SelectedFile");

        //retrieve the file data
        InputStream stream = selectedFile.getInputStream();
        // todo @bc file name
        String filename = System.currentTimeMillis() + "-" + getFileName(selectedFile);


        //write the file to the file specified
        OutputStream bos = new FileOutputStream(uploadDir + filename);
        int bytesRead;
        byte[] buffer = new byte[8192];

        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.close();

        //close the stream
        stream.close();

        // place the data into the request for retrieval on next page
        request.setAttribute("friendlyName", name);
        request.setAttribute("fileName", filename);
//        request.setAttribute("contentType", file.getContentType());
//        request.setAttribute("size", file.getSize() + " bytes");
//        request.setAttribute("location", dirPath.getAbsolutePath() + Constants.FILE_SEP + filename);
//
        String linkDir = request.getContextPath() + "/resources" + "/" + request.getRemoteUser() + "/";
        String linkPath = linkDir + filename;

        HashMap<Object, Object> info = new HashMap<>();
        info.put("link", linkPath);
        return JSONObject.valueToString(info);
    }

    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
