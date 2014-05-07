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
import android.widget.BaseExpandableListAdapter;

public class InjectExpandableListAdapter<Group extends InjectExpandableListAdapter.GetChildList<Child>, Child, GroupHolder extends InjectExpandableListAdapter.GroupViewHolder<Group>, ChildHolder extends InjectExpandableListAdapter.ChildViewHolder<Child>> extends BaseExpandableListAdapter {
    private Context context;
    private List<Group> dataList;
    private Class<GroupHolder> groupHolderClass;
    private Class<ChildHolder> childHolderClass;
    private BindingEventListener<GroupHolder> groupBindingEventListener;
    private BindingEventListener<ChildHolder> childBindingEventListener;

    public InjectExpandableListAdapter(Context context, Class<GroupHolder> groupHolderClass, Class<ChildHolder> childHolderClass, List<Group> dataList, BindingEventListener<GroupHolder> groupBindingEventListener, BindingEventListener<ChildHolder> childBindingEventListener) {
        this.context = context;
        this.dataList = dataList;
        this.groupHolderClass = groupHolderClass;
        this.childHolderClass = childHolderClass;
        this.groupBindingEventListener = groupBindingEventListener;
        this.childBindingEventListener = childBindingEventListener;
    }

    public InjectExpandableListAdapter(Context context, Class<GroupHolder> groupHolderClass, Class<ChildHolder> childHolderClass, List<Group> dataList) {
        this(context, groupHolderClass, childHolderClass, dataList, null, null);
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        try {
            GroupHolder groupViewHolder;
            if(convertView == null){
                InjectContentView injectContentView = groupHolderClass.getAnnotation(InjectContentView.class);
                convertView = LayoutInflater.from(context).inflate(injectContentView.value(), null);
                groupViewHolder = groupHolderClass.newInstance();
                convertView.setTag(groupViewHolder);
                for(Field field : groupHolderClass.getDeclaredFields()){
                    if(field.isAnnotationPresent(InjectView.class)){
                        field.setAccessible(true);
                        field.set(groupViewHolder, convertView.findViewById(field.getAnnotation(InjectView.class).value()));
                    }
                }
                if(groupBindingEventListener != null){
                    groupBindingEventListener.bindingEvent(groupViewHolder);
                }
            }else{
                groupViewHolder = (GroupHolder) convertView.getTag();
            }
            groupViewHolder.setValues(context, groupPosition, isExpanded, dataList.get(groupPosition));
            return convertView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        try {
            ChildHolder childViewHolder;
            if(convertView == null){
                InjectContentView injectContentView = childHolderClass.getAnnotation(InjectContentView.class);
                convertView = LayoutInflater.from(context).inflate(injectContentView.value(), null);
                childViewHolder = childHolderClass.newInstance();
                convertView.setTag(childViewHolder);
                for(Field field : childHolderClass.getDeclaredFields()){
                    if(field.isAnnotationPresent(InjectView.class)){
                        field.setAccessible(true);
                        field.set(childViewHolder, convertView.findViewById(field.getAnnotation(InjectView.class).value()));
                    }
                }
                if(childBindingEventListener != null){
                    childBindingEventListener.bindingEvent(childViewHolder);
                }
            }else{
                childViewHolder = (ChildHolder) convertView.getTag();
            }
            childViewHolder.setValues(context, groupPosition, childPosition, isLastChild, dataList.get(groupPosition).getChildList().get(childPosition));
            return convertView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Group> getDataList() {
        return dataList;
    }

    public interface GetChildList<E>{
        public List<E> getChildList();
    }

    public interface GroupViewHolder<E>{
        public void setValues(Context context, int groupPosition, boolean isExpanded, E e);
    }

    public interface ChildViewHolder<E>{
        public void setValues(Context context, int groupPosition, int childPosition, boolean isLastChild, E e);
    }
}
