package com.cc.app.ui.base;

import java.util.LinkedList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 基于所有BaseAdapter 扩展的基类；
 * 本程序可用此AdapterBase
 * @author wenchao
 *
 * @param <T>
 */
public abstract class AdapterBase<T> extends BaseAdapter {
	
	private final List<T> mList = new LinkedList<T>();

	/**
	 * 获得list列表
	 * @return
	 */
	public List<T> getList(){
		return mList;
	}
	
	/**
	 * 设置list列表
	 * @param list
	 */
	public void setList(List<T> list){
		clear();
		appendToList(list);
	}
	
	
	public void appendToList(List<T> list){
		if(list == null){
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}
	
	public void clear(){
		mList.clear();
		notifyDataSetChanged();
	}
	
	public void appendToTopList(List<T> list){
		if(list == null)
			return;
		mList.addAll(0,list);
		notifyDataSetChanged();
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(position == getCount()-1){
			onReachBottom();
		}
		return getExView(position, convertView, parent);
	}
	
	/**
	 * 重写此方法，返回item
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	protected abstract View getExView(int position,View convertView,ViewGroup parent);
	
	/**
	 * 达到底部回调
	 */
	protected abstract void onReachBottom();
	

}
