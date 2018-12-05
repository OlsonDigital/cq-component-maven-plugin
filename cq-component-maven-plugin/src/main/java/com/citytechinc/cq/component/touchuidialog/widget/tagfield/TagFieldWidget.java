/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.widget.tagfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

/**
 * Touch UI Tag Field Widget
 * <p>
 * This widget is rendered in response to the @TagInputField annotation when the tagfieldwidget feature flag is enabled.
 */
@TouchUIWidget(annotationClass = TagInputField.class, makerClass = TagFieldWidgetMaker.class,
    resourceType = TagFieldWidget.RESOURCE_TYPE, featureFlag = TagFieldWidget.FEATURE_FLAG)
public class TagFieldWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "cq/gui/components/coral/common/form/tagfield";

    public static final String FEATURE_FLAG = "tagfieldwidget";

    private String rootPath;

    private boolean multiple;

    public TagFieldWidget(TagFieldWidgetParameters parameters) {
        super(parameters);

        this.rootPath = parameters.getRootPath();
        this.multiple = parameters.isMultiple();
    }

    public String getRootPath() {
        return rootPath;
    }

    public boolean isMultiple() {
        return multiple;
    }
}
