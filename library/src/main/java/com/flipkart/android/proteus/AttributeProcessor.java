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

package com.flipkart.android.proteus;

import android.content.Context;

import com.flipkart.android.proteus.toolbox.Scope;
import com.google.gson.JsonElement;

/**
 * @author kirankumar
 * @author aditya.sharat
 */
public abstract class AttributeProcessor<V> {

    public void process(V view, Value value) {
        if (value.isBinding()) {
            Scope scope = ((ProteusView) view).getViewManager().getScope();
            Value resolved = evaluate(value.getAsBinding(), scope.getData(), scope.getIndex());
            handleValue(view, resolved);
        } else if (value.isResource()) {
            handleResource(view, value.getAsResource());
        } else if (value.isStyle()) {
            handleStyleAttribute(view, value.getAsStyleAttribute());
        } else {
            handleValue(view, value);
        }
    }

    public abstract void handleValue(V view, Value value);

    public abstract void handleResource(V view, Resource resource);

    public abstract void handleStyleAttribute(V view, StyleAttribute style);

    public Value parse(Value value, Context context) {
        return value;
    }

    protected Value evaluate(Binding binding, JsonElement data, int index) {
        return binding.evaluate(data, index);
    }

}
