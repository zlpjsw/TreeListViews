package com.zlp.treelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2015/12/24.
 */
public class TreeUnFoldAdapter extends BaseAdapter {
    /**
     * 所有的数据集合
     */
    private ArrayList<TreeNode> allNodes;
    private Context context;
    /**
     * item的行首缩进基数
     */
    private int indentionBase;

    public TreeUnFoldAdapter( Context context,ArrayList<TreeNode> allNodes) {
        this.allNodes = allNodes;
        this.context = context;
        indentionBase = 20;
        // 获取 数据源 时 进行 排序
        bubbleSort(this.allNodes);
    }

    /**
     * 树形 结构 排序
     * @param allNodes
     */
    private void treeSort(ArrayList<TreeNode> allNodes) {
        for (int index = 0; index < allNodes.size(); index++) {
            for (int i = index + 1; i < allNodes.size(); i++) {
                if (allNodes.get(index).getId() == allNodes.get(i).getParendId()) {
                    TreeNode temp = allNodes.get(i);
                    allNodes.remove(i);
                    allNodes.add(index + 1, temp);
                }
            }
        }
    }

    /**
     *  冒泡 排序
     * @param allNodes
     */
    private void bubbleSort(ArrayList<TreeNode> allNodes) {
        for (int i = 0; i < allNodes.size(); i++) {
            for (int j = 0; j < allNodes.size() - i - 1; j++) {
                if (allNodes.get(j).getLevel() > allNodes.get(j + 1).getLevel()) {
                    TreeNode temp = allNodes.get(j);
                    allNodes.set(j, allNodes.get(j + 1));
                    allNodes.set(j + 1, temp);
                }
            }
        }
        treeSort(allNodes);
    }

    @Override
    public int getCount() {
        return allNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return allNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.tree_item, null);
            holder.homeImg = (ImageView) convertView.findViewById(R.id.homeImg);
            holder.treeText = (TextView) convertView.findViewById(R.id.treeText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TreeNode element = allNodes.get(position);
        int level = element.getLevel();
        holder.homeImg.setPadding(
                indentionBase * (level + 1),
                holder.homeImg.getPaddingTop(),
                holder.homeImg.getPaddingRight(),
                holder.homeImg.getPaddingBottom());
        holder.treeText.setText(element.getContentText());
        holder.homeImg.setImageResource(R.mipmap.ic_launcher);
        //这里要主动设置一下icon可见，因为convertView有可能是重用了"设置了不可见"的view，下同。
        holder.homeImg.setVisibility(View.VISIBLE);
        return convertView;
    }

    class ViewHolder {
        ImageView homeImg;
        TextView treeText;
    }
}