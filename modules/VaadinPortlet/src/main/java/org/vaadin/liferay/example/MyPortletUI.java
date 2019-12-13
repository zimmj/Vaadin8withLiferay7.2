package org.vaadin.liferay.example;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.vaadin.liferay.example.ui.widgetset.server.StripCharsRe;


@Widgetset(AppWidgetSet.NAME)
@SuppressWarnings("serial")

@Component(
        property = {
                "com.liferay.portlet.display-category=Vaadin Example",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.name=org_vaadin_liferay_example_MyPortletUI",
                "javax.portlet.display-name=Working Vaadin Portlet in Liferay 7.2",
                "javax.portlet.security-role-ref=power-user,user",
                "com.vaadin.osgi.liferay.portlet-ui=true"

        },
        scope = ServiceScope.PROTOTYPE,
        service = UI.class
)
public class MyPortletUI extends UI {

    private static Log log = LogFactoryUtil.getLog(MyPortletUI.class);


    @Override
    protected void init(VaadinRequest request) {
        final String portletContextName = getPortletContextName(request);
        final Integer numOfRegisteredUsers = getPortalCountOfRegisteredUsers();
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        setContent(layout);

        TextField textField = new TextField();
        textField.setCaption("Max for digits");
        StripCharsRe.init(textField).setRegExp("-*\\D.*|.{5,}");
        layout.addComponent(textField);

        final Button button = new Button("Click Me");
        button.addClickListener(event -> layout.addComponent(new Label(
                "Hello, World!<br>This is portlet "
                        + portletContextName
                        + "made with Liferay Gradle workspace.<br>This portal has "
                        + numOfRegisteredUsers
                        + " registered users (according to the data returned by Liferay API call).",
                ContentMode.HTML))
        );
        layout.addComponent(button);
    }

    private String getPortletContextName(VaadinRequest request) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) request
                .getWrappedSession();
        PortletSession portletSession = wrappedPortletSession
                .getPortletSession();

        final PortletContext context = portletSession.getPortletContext();
        return context.getPortletContextName();
    }

    private Integer getPortalCountOfRegisteredUsers() {
        Integer result = null;

        try {
            result = UserLocalServiceUtil.getUsersCount();
        } catch (SystemException e) {
            log.error(e);
        }

        return result;
    }
}
