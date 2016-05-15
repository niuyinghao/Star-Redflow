package my.webapp.action;

import my.model.persist.place.Stone;
import my.model.persist.place.Wish;
import my.model.wrapper.MoundTarget;
import my.service.WishManager;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tree.Tree;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */


@Component
@Scope("view")
public class AreaViewScopeContainer implements Serializable {
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	// members
	public static final String SEPARATOR = "_";
	Tree moundSelectTargetTreeUI;
	public TreeNode moundTargetTreeRoot;
	TreeNode[] selectedMoundTargetNodes;
	@Autowired
	private MoundTarget moundTarget;
	DataTable waveDataTable;
	@Autowired
	private WishManager wishManager;
	private TreeNode selectedNode;


	@PostConstruct
	public void init() {
		_setTreeRoot();
	}

	public void _setTreeRoot() {
		if (moundTargetTreeRoot == null) {
			moundTargetTreeRoot = new DefaultTreeNode(null);
		}
		moundTargetTreeRoot.setExpanded(false);
		List allWaveOrFlowerNotMound = moundTarget.getAllWaveOrFlowerNotMound();
		for (Object o : allWaveOrFlowerNotMound) {
			DefaultTreeNode node = new DefaultTreeNode(o);
			if (o instanceof Wish) {
				node.setExpanded(false);
				node.getChildren().add(new DefaultTreeNode(null));
			}
			moundTargetTreeRoot.getChildren().add(node);
		}
	}

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
		} else {
			String relativeRowKey = rowKey.substring(rowKey.indexOf(SEPARATOR) + 1);

			return findTreeNode(searchRoot, relativeRowKey);
		}
	}

	//getter and setter
	public DataTable getWaveDataTable() {
		return waveDataTable;
	}

	public void setWaveDataTable(DataTable waveDataTable) {
		this.waveDataTable = waveDataTable;
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
		} else {
			children.clear();
		}
		Object data = _this.getData();

		if (data instanceof Wish) {
			List<Stone> stoneList = wishManager.getStones((Wish) data);
			for (Stone stone : stoneList) {
				DefaultTreeNode node = new DefaultTreeNode(stone, _this);
				children.add(node);
			}
		}

/*
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
        */
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
}

