package my.webapp.action;

import my.model.persist.place.Flower;
import my.model.persist.place.Stone;
import my.model.persist.place.Wave;
import my.model.persist.place.Wish;
import my.model.wrapper.MoundTarget;
import org.primefaces.component.tree.Tree;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */


@Component
@Scope("view")
public class AreaViewScopeContainer {
    public static final String SEPARATOR = "_";
    Tree moundSelectTargetTreeUI;
    public TreeNode moundTargetTreeRoot;
    TreeNode[] selectedMoundTargetNodes;
    @Autowired
    private MoundTarget moundTarget;

    public void onMoundSelectTreeExpand() {
        Tree tree = moundSelectTargetTreeUI;
        TreeNode _this = null;
        FacesContext context = FacesContext.getCurrentInstance();
        TreeNode root = tree.getValue();
        if (tree.isNodeExpandRequest(context)) {
            String clientId = tree.getClientId(context);
            Map<String, String> params = context.getExternalContext().getRequestParameterMap();
            String rowKey = params.get(clientId + "_expandNode");

//			tree.setRowKey(rowKey);
//			_this = tree.getRowNode();
//			_this.setExpanded(false);
//			tree.setRowKey(null);

            _this = findTreeNode(root, rowKey);
        }


        setChildrenNode(_this);

        tree.setValue(root);
    }

    protected TreeNode findTreeNode(TreeNode searchRoot, String rowKey) {
        if (rowKey == null || searchRoot == null) {
            return null;
        }

        if (rowKey.equals("root")) {
            return searchRoot;
        }

        String[] paths = rowKey.split(SEPARATOR);

        if (paths.length == 0)
            return null;

        int childIndex = Integer.parseInt(paths[0]);
        if (childIndex >= searchRoot.getChildren().size())
            return null;

        searchRoot = searchRoot.getChildren().get(childIndex);

        if (paths.length == 1) {
            return searchRoot;
        }
        else {
            String relativeRowKey = rowKey.substring(rowKey.indexOf(SEPARATOR) + 1);

            return findTreeNode(searchRoot, relativeRowKey);
        }
    }

    public TreeNode[] getSelectedMoundTargetNodes() {
        return selectedMoundTargetNodes;
    }

    public void setSelectedMoundTargetNodes(TreeNode[] selectedMoundTargetNodes) {
        this.selectedMoundTargetNodes = selectedMoundTargetNodes;
    }

    public MoundTarget getMoundTarget() {
        return moundTarget;
    }

    public void setMoundTarget(MoundTarget moundTarget) {
        this.moundTarget = moundTarget;
    }

    public TreeNode getMoundTargetTreeRoot() {
        if (moundTargetTreeRoot == null) {
            moundTargetTreeRoot = new DefaultTreeNode(null);
        }
        //// TODO: 2016/5/4  
        moundTarget.getAllWaveOrFlower();
        return moundTargetTreeRoot;
    }

    public void setMoundTargetTreeRoot(TreeNode moundTargetTreeRoot) {
        this.moundTargetTreeRoot = moundTargetTreeRoot;
    }

    public Tree getMoundSelectTargetTree() {
        return moundSelectTargetTreeUI;
    }

    public void setMoundSelectTargetTree(Tree moundSelectTargetTree) {
        this.moundSelectTargetTreeUI = moundSelectTargetTree;
    }

    private void setChildrenNode(TreeNode _this) {
        List<TreeNode> children = _this.getChildren();

        if (children == null) {
            children = new ArrayList();
        }
        else {
            children.clear();
        }
        Object data = _this.getData();

        if (data instanceof Wish) {
            for (Stone stone : ((Wish) data).getStoneList()) {
                DefaultTreeNode node = new DefaultTreeNode(stone, _this);
                children.add(node);
            }
        }
        else if (data instanceof Wave) {
            for (Wish wish : ((Wave) data).getWishes()) {
                DefaultTreeNode node = new DefaultTreeNode(wish, _this);
                children.add(node);
            }
        }
        else if (data instanceof Flower) {
            for (Wish wish : ((Flower) data).getWishes()) {
                DefaultTreeNode node = new DefaultTreeNode(wish, _this);
                children.add(node);
            }
        }
    }
}

