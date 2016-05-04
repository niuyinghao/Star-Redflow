package my.webapp.action;

import my.Constants;
import my.model.persist.BaseLog;
import my.model.persist.Setting;
import my.model.persist.User;
import my.model.persist.place.Flower;
import my.model.persist.place.Mound;
import my.model.persist.place.Stone;
import my.model.persist.place.Wave;
import my.model.persist.place.Wish;
import my.model.persist.project.Pray;
import my.model.wrapper.CommonContext;
import my.model.wrapper.Context;
import my.model.wrapper.Grail;
import my.model.wrapper.Hillock;
import my.model.wrapper.MoundTarget;
import my.model.wrapper.Plain;
import my.model.wrapper.Sea;
import my.model.wrapper.Swamp;
import my.service.AreaManager;
import my.service.FlowerManager;
import my.service.GenericManager;
import my.service.MoundManager;
import my.service.StoneManager;
import my.service.UserManager;
import my.service.WaveManager;
import my.service.WishManager;
import my.webapp.util.WebUtil;
import org.primefaces.component.tree.Tree;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */


@Component
@Scope("session")
@Lazy
public class AreaPage extends BasePage implements Serializable {
    public TreeNode moundTargetTreeRoot;
    // members
    Wave wave = new Wave();
    Flower flower = new Flower();
    Mound mound = new Mound();
    Stone stone = new Stone();
    Wish wish = new Wish();
    TreeNode[] selectedMoundTargetNodes;
    Tree moundSelectTargetTreeUI;
    @Autowired
    private WaveManager waveManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private Sea sea;
    @Autowired
    private Swamp swamp;
    @Autowired
    private Plain plain;
    @Autowired
    private Hillock hillock;
    @Autowired
    private Grail grail;
    String backgroundColor = "red";
    @Autowired
    private WishManager wishManager;
    BaseLog wishBelong;
    @Autowired
    Setting setting;
    @Autowired
    private FlowerManager flowerManager;
    @Autowired
    private MoundManager moundManager;
    @Autowired
    private StoneManager stoneManager;
    private DashboardModel plainBoard;
    @Autowired
    private MoundTarget moundTarget;
    List<BaseLog> selectedMoundTarget;
    @Autowired
    private CommonContext areaContext;
    boolean needBackToHistory = false;
    String mostRecentHistory = "";
    @Autowired
    private AreaManager areaManager;
    public static final String SEPARATOR = "_";

    public List<Pray> delegatePrays(Wish wish) {
        List<Pray> prays = wish.getPrays();
        prays.add(new Pray());
        return prays;
    }

    public void firePray(Wish wish) {
        List<Pray> prays = wish.getPrays();
        prays.add(new Pray());
        wishManager.save(wish);
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

    public String genMoundContent(Mound mound) {
        StringBuffer sb = new StringBuffer();
        for (BaseLog baseLog : mound.getTargets()) {
            sb.append(baseLog.getId());
            sb.append("|");
            String content = baseLog.getContent();
            sb.append(beautify(content));
            sb.append("\n");
        }
        return sb.toString();
    }

    private String beautify(String content) {
        if (content.length() > Constants.STRING_BEAUTIFY_LENGTH) {
            return content.substring(0, Constants.STRING_BEAUTIFY_LENGTH) + "...";
        }
        else {
            return content;
        }
    }

    public void alterMoundToolIndex(Mound mound, int toolIndex) {
        mound.setToolIndex(toolIndex);
    }

    public void backToViewMode(Context wrapper) throws IOException {
        wrapper.setEditMode(false);
        handleRedirHistory();
    }

    private void handleRedirHistory() throws IOException {
        if (needBackToHistory) {
            needBackToHistory = !needBackToHistory;
            FacesContext.getCurrentInstance().getExternalContext().redirect(mostRecentHistory);
        }
    }

    public String getBuryColor(int depth) {
        int value = (int) (((float) depth / Constants.MOUND_BURIED_BOUND) * 255);
        String hex = Integer.toHexString(255 - value).toUpperCase();
        return "#" + hex + hex + hex;
    }

    public void enterEditMode(Context wrapper) {
        wrapper.setEditMode(true);
    }

    public void onDialogChooseMoundTargetReturn(SelectEvent event) {
    }

    public void doDialogChooseMoundTargetClose() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        mound.setTargets(selectedMoundTarget);
        mound.setCreator(areaContext.getCreator());
        mound.setCreateTime(new Date());
        addMound(mound);
        RequestContext.getCurrentInstance().closeDialog(selectedMoundTarget);
    }

    public void addMound(Mound mound) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        moundManager.save(mound);
    }

    public void addMinusBuryCountOrChooseTarget(Mound mound) {
        int toolIndex = mound.getToolIndex();
        boolean needPersist = false;
        if (toolIndex == Constants.TOOL_INDEX_MOUND_BURY) {
            if (mound.getBuryDepth() >= Constants.MOUND_BURIED_BOUND) {
                mound.setBuryDepth(Constants.MOUND_BURIED_BOUND);
                return;
            }
            mound.setBuryDepth(mound.getBuryDepth() + 1);
            needPersist = true;
        }
        else if (toolIndex == Constants.TOOL_INDEX_MOUND_UN_BURY) {
            if (mound.getBuryDepth() <= Constants.MOUND_BURIED_UN_BOUND) {
                mound.setBuryDepth(Constants.MOUND_BURIED_UN_BOUND);
                return;
            }
            mound.setBuryDepth(mound.getBuryDepth() - 1);
            needPersist = true;
        }
        else {
            mound.setToolIndex(Constants.TOOL_INDEX_MOUND_CHOOSE_TARGET);
            RequestContext.getCurrentInstance().openDialog("/main/misc/plain/chooseMoundTarget.xhtml");
        }
        ;
        if (needPersist) {
            persistMoundBury(mound);
        }
    }

    private void persistMoundBury(Mound mound) {
        for (BaseLog baseLog : mound.getTargets()) {
            baseLog.setBuryDepth(mound.getBuryDepth());
            areaManager.updateBaseObj(baseLog);
        }
        areaManager.updateBaseObj(mound);
    }

    //method
    @PostConstruct
    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ServletException, IOException {
//        String remoteUser = getRequest().getRemoteUser();
        User user = WebUtil.getCurrentUser(getRequest());
        if (user == null) {
            return;
        }

        plainBoard = new DefaultDashboardModel();
        DashboardColumn column = new DefaultDashboardColumn();
        column.addWidget("mound");
        plainBoard.addColumn(column);


        // context
        sea.setTargetClass(Wave.class);
        swamp.setTargetClass(Flower.class);
        plain.setTargetClass(Mound.class);
        hillock.setTargetClass(Stone.class);
        grail.setTargetClass(Wish.class);
        areaContext.setCreator(user);
    }

    public void addWave(Wave wave) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        genericSave(wave, waveManager, sea);
    }

    private void genericSave(BaseLog base, GenericManager manager, Context wrapper) {
        base.setCreator(areaContext.getCreator());
        base.setCreateTime(new Date());
        Serializable id = manager.save(base);
        wrapper.setCurrentIndex(0);
        wrapper.setEditMode(false);
        try {
            handleRedirHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFlower(Flower flower) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        genericSave(flower, flowerManager, swamp);
    }

    public void addStone(Stone stone) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        genericSave(stone, stoneManager, hillock);
    }

    public void addWish(Wave belong, Wish wish) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        wish.setBelongWave(belong);
        saveWish(wish);
    }

    private void saveWish(Wish wish) {
        if (wish.getBelongWave() != null && wish.getBelongWave().getId() == null) {
            wish.setBelongWave(null);
        }
        if (wish.getBelongFlower() != null && wish.getBelongFlower().getId() == null) {
            wish.setBelongFlower(null);
        }
        genericSave(wish, wishManager, grail);
        this.wish = new Wish();
    }

    public void addWish(Flower belong, Wish wish) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        wish.setBelongFlower(belong);
        saveWish(wish);
    }

    public String fireWish(BaseLog belong) {
        this.wishBelong = belong;
        grail.setEditMode(true);
        needBackToHistory = true;
        mostRecentHistory = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return "/main/starry/bless.xhtml?faces-redirect=true";
    }

    public Tree getMoundSelectTargetTree() {
        return moundSelectTargetTreeUI;
    }

    public void setMoundSelectTargetTree(Tree moundSelectTargetTree) {
        this.moundSelectTargetTreeUI = moundSelectTargetTree;
    }

    public TreeNode[] getSelectedMoundTargetNodes() {
        return selectedMoundTargetNodes;
    }

    public void setSelectedMoundTargetNodes(TreeNode[] selectedMoundTargetNodes) {
        this.selectedMoundTargetNodes = selectedMoundTargetNodes;
    }

    public TreeNode getMoundTargetTreeRoot() {
        if (moundTargetTreeRoot == null) {
            moundTargetTreeRoot = new DefaultTreeNode(null);
        }

        return moundTargetTreeRoot;
    }

    public void setMoundTargetTreeRoot(TreeNode moundTargetTreeRoot) {
        this.moundTargetTreeRoot = moundTargetTreeRoot;
    }

    //getter and setter
    public List<BaseLog> getSelectedMoundTarget() {
        return selectedMoundTarget;
    }

    public void setSelectedMoundTarget(List<BaseLog> selectedMoundTarget) {
        this.selectedMoundTarget = selectedMoundTarget;
    }

    public MoundTarget getMoundTarget() {
        return moundTarget;
    }

    public void setMoundTarget(MoundTarget moundTarget) {
        this.moundTarget = moundTarget;
    }

    public CommonContext getAreaContext() {
        return areaContext;
    }

    public void setAreaContext(CommonContext areaContext) {
        this.areaContext = areaContext;
    }

    public DashboardModel getPlainBoard() {
        return plainBoard;
    }

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    public Swamp getSwamp() {
        return swamp;
    }

    public void setSwamp(Swamp swamp) {
        this.swamp = swamp;
    }

    public Plain getPlain() {
        return plain;
    }

    public void setPlain(Plain plain) {
        this.plain = plain;
    }

    public Hillock getHillock() {
        return hillock;
    }

    public void setHillock(Hillock hillock) {
        this.hillock = hillock;
    }

    public Grail getGrail() {
        return grail;
    }

    public void setGrail(Grail grail) {
        this.grail = grail;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Mound getMound() {
        return mound;
    }

    public void setMound(Mound mound) {
        this.mound = mound;
    }

    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
    }

    public BaseLog getWishBelong() {
        return wishBelong;
    }

    public void setWishBelong(BaseLog wishBelong) {
        this.wishBelong = wishBelong;
    }

    public Wave getWave() {
        return wave;
    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Sea getSea() {
        return sea;
    }

    public void setSea(Sea sea) {
        this.sea = sea;
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

