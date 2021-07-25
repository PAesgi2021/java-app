package plugins;

import fr.java.client.IPlugin;
import fr.java.client.services.Instance;
import fr.java.client.utils.ExportTasksToPDF;

public class myPlugin implements IPlugin {
    /**
     * Prints out the content of the system property “user.home”.
     */
    public void run() {
        System.out.println(" File will be exported to directory of the app ./TasksExportPdf.pdf");
        Instance instance = Instance.getInstance();
        ExportTasksToPDF exportTasksToPDF = new ExportTasksToPDF();
        exportTasksToPDF.create(instance.getSpaceService().getCurrentSpace().getTodolists());
    }
}
