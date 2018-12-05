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

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class TagFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    private String rootPath;

    private boolean multiple;

    @Override
    public String getResourceType() {
        return TagFieldWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException();
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(final boolean multiple) {
        this.multiple = multiple;
    }
}
