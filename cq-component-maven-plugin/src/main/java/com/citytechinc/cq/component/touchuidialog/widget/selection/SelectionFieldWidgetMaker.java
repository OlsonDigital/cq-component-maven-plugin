package com.citytechinc.cq.component.touchuidialog.widget.selection;

import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.util.TouchUIDialogUtil;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.radiogroup.RadioGroupWidgetMaker;
import org.codehaus.plexus.util.StringUtils;

public class SelectionFieldWidgetMaker extends AbstractTouchUIWidgetMaker<SelectionFieldWidgetParameters> {

    public SelectionFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(SelectionFieldWidgetParameters widgetParameters) throws ClassNotFoundException,
        InvalidComponentFieldException, TouchUIDialogGenerationException, IllegalAccessException,
        InstantiationException {

        Selection selectionField = getAnnotation(Selection.class);

        if (Selection.RADIO.equals(selectionField.type())) {
            return makeRadioGroup();
        } else {
            if (StringUtils.isNotBlank(selectionField.type()) && !Selection.SELECT.equals(selectionField.type())) {
                LogSingleton
                    .getInstance()
                    .warn(
                        "Selection field type "
                            + selectionField.type()
                            + " requested for field "
                            + getFieldNameForField()
                            + " however no such presentation is implemented for the Touch UI.  Defaulting to a dropdown selection.");
            }
            return makeSelection(selectionField, widgetParameters);
        }

    }

    protected TouchUIDialogElement makeRadioGroup() throws TouchUIDialogGenerationException,
        InvalidComponentFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        RadioGroupWidgetMaker maker = new RadioGroupWidgetMaker(parameters);
        return maker.make();
    }

    protected TouchUIDialogElement makeSelection(Selection selectionField,
        SelectionFieldWidgetParameters widgetParameters) throws InvalidComponentFieldException {

        widgetParameters.setMultiple(getMultipleForField(selectionField));
        widgetParameters.setDataSource(getDataSourceForField(selectionField));
        widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

        if (widgetParameters.getDataSource() == null) {
            widgetParameters.setOptions(TouchUIDialogUtil
                .getOptionsForSelection(selectionField, widgetParameters, getType(), parameters.getClassLoader(),
                    parameters.getClassPool()));
        }

        if (TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
            return new SelectionFieldCoral3Widget(widgetParameters);
        }
        return new SelectionFieldWidget(widgetParameters);
    }

    public boolean getMultipleForField(Selection annotation) {
        if (annotation != null) {
            return annotation.multiple();
        }

        return false;
    }

    public DataSource getDataSourceForField(Selection annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.dataSource())) {
            DataSourceParameters dataSourceParameters = new DataSourceParameters();
            dataSourceParameters.setResourceType(annotation.dataSource());
            return new DataSource(dataSourceParameters);
        }

        return null;
    }

}
