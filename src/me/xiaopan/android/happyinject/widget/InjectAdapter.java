/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.android.happyinject.widget;

import java.lang.reflect.Field;
import java.util.List;

import me.xiaopan.android.happyinject.InjectContentView;
import me.xiaopan.android.happyinject.InjectView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class InjectAdapter<Data, Holder extends InjectAdapter.ViewHolder<Data>> extends BaseAdapter{
    private Context context;
    private List<Data> dataList;
    private Class<Holder> holderClass;
    private BindingEventListener<Holder> bindingEventListener;

    public InjectAdapter(Context context, Class<Holder> holderClass, List<Data> dataList, BindingEventListener<Holder> bindingEventListener) {
        this.context = context;
        this.dataList = dataList;
        this.holderClass = holderClass;
        this.bindingEventListener = bindingEventListener;
        if(!holderClass.isAnnotationPresent(InjectContentView.class)){
            throw new IllegalArgumentException(" Not found InjectContentView Annotation in "+ holderClass.getName());
        }
    }

    public InjectAdapter(Context context, Class<Holder> holderClass, List<Data> dataList) {
        this(context, holderClass, dataList, null);
    }

    @Override
    public int getCount() {
        return dataList !=null? dataList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return dataList !=null? dataList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
	@Override
    public View getView(int position, View convertView, ViewGroup parent){
        try {
            Holder viewHolder;
            if(convertView == null){
                InjectContentView injectContentView = holderClass.getAnnotation(InjectContentView.class);
                convertView = LayoutInflater.from(context).inflate(injectContentView.value(), null);
                viewHolder = holderClass.newInstance();
                convertView.setTag(viewHolder);
                for(Field field : holderClass.getDeclaredFields()){
                    if(field.isAnnotationPresent(InjectView.class)){
                        field.setAccessible(true);
                        field.set(viewHolder, convertView.findViewById(field.getAnnotation(InjectView.class).value()));
                    }
                }
                if(bindingEventListener != null){
                    bindingEventListener.bindingEvent(viewHolder);
                }
            }else{
                viewHolder = (Holder) convertView.getTag();
            }
            viewHolder.setValues(context, dataList.get(position));
            return convertView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public interface ViewHolder<E>{
        public void setValues(Context context, E e);
    }
}
