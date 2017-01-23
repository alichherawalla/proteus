/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 * Copyright (c) 2017 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.flipkart.android.proteus.parser.custom;

import android.view.ViewGroup;
import android.webkit.WebView;

import com.flipkart.android.proteus.Layout;
import com.flipkart.android.proteus.ProteusLayoutInflater;
import com.flipkart.android.proteus.ProteusView;
import com.flipkart.android.proteus.TypeParser;
import com.flipkart.android.proteus.processor.StringAttributeProcessor;
import com.flipkart.android.proteus.toolbox.Attributes;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusWebView;
import com.google.gson.JsonObject;

/**
 * Created by kiran.kumar on 12/05/14.
 */
public class WebViewParser<T extends WebView> extends TypeParser<T> {

    @Override
    public ProteusView createView(ProteusLayoutInflater inflater, ViewGroup parent, Layout layout, JsonObject data, Styles styles, int index) {
        return new ProteusWebView(parent.getContext());
    }

    @Override
    protected void addAttributeProcessors() {

        addAttributeProcessor(Attributes.WebView.Url, new StringAttributeProcessor<T>() {
            @Override
            public void setString(T view, String value) {
                view.loadUrl(value);
            }
        });
        addAttributeProcessor(Attributes.WebView.HTML, new StringAttributeProcessor<T>() {
            @Override
            public void setString(T view, String value) {
                view.loadData(value, "text/html", "UTF-8");
            }
        });
    }
}
