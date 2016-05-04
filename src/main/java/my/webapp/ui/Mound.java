package my.webapp.ui;

import org.primefaces.component.panel.Panel;

/**
 * Created by niuyinghao on 2016/4/19 for project.
 */
public class Mound extends Panel{
    @Override
    public String getFamily() {
        return "my.Mound";
    }

    @Override
    public String getRendererType() {
        return "my.MoundRender";
    }
}
