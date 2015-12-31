package com.zlp.treelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UnFoldingListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    TreeUnFoldAdapter  treeUnFoldAdapter;
    ArrayList<TreeNode> allNodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_folding_list_view);
        listView = (ListView) findViewById(R.id.tree_list);
        init();
        treeUnFoldAdapter = new TreeUnFoldAdapter(this,allNodes);


        listView.setAdapter(treeUnFoldAdapter);

        listView.setOnItemClickListener(this);

    }

    private void init() {
        allNodes = new ArrayList<TreeNode>();

        //添加节点  -- 节点名称，节点level，节点id，父节点id，是否有子节点，是否展开

        //添加最外层节点
        TreeNode node1= new TreeNode("北京市", TreeNode.TOP_LEVEL, 0, TreeNode.NO_PARENT, true, false);

        //添加第一层节点
        TreeNode node2= new TreeNode("海淀区", TreeNode.TOP_LEVEL + 1, 1, node1.getId(), true, false);
        TreeNode node10= new TreeNode("海淀区dsfas", TreeNode.TOP_LEVEL + 1, 10, node1.getId(), true, false);
        //添加第二层节点
        TreeNode node3= new TreeNode("西二旗", TreeNode.TOP_LEVEL + 2, 2, node2.getId(), true, false);
        TreeNode node13= new TreeNode("西二旗dfasd", TreeNode.TOP_LEVEL + 2, 20, node10.getId(), true, false);
        //添加第三层节点
		TreeNode node7= new TreeNode("辉煌国际", TreeNode.TOP_LEVEL + 3, 6, node3.getId(), false, false);

        //添加第一层节点
        TreeNode node4= new TreeNode("河南省", TreeNode.TOP_LEVEL , 3, TreeNode.NO_PARENT, true, false);
        //添加第二层节点
        TreeNode node5= new TreeNode("郑州市", TreeNode.TOP_LEVEL + 1, 4, node4.getId(), true, false);
        //添加第三层节点
        TreeNode node6= new TreeNode("金水区", TreeNode.TOP_LEVEL + 2, 5, node5.getId(), false, false);
        //创建数据源
        allNodes.add(node1);
        allNodes.add(node2);
        allNodes.add(node3);
        allNodes.add(node4);
        allNodes.add(node5);
        allNodes.add(node6);
		allNodes.add(node7);
		allNodes.add(node10);
		allNodes.add(node13);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,allNodes.get(position).getContentText()+"",Toast.LENGTH_LONG).show();
    }
}
