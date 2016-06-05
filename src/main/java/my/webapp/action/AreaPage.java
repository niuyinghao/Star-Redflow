package my.webapp.action;

import my.Constants;
import my.model.persist.BaseLog;
import my.model.persist.User;
import my.model.persist.project.HeartSymbol;
import my.model.persist.spirit.*;
import my.model.persist.project.Pray;
import my.model.wrapper.*;
import my.service.*;
import my.util.HeartSymbolResolver;
import my.webapp.util.WebUtil;
import org.primefaces.component.api.UIData;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */


@Component
@Scope("session")
@Lazy
public class AreaPage extends BasePage implements Serializable {
    // members
    Wave newWave;
    Flower newFlower;
    Mound newMound;
    Stone newStone;
    Wish newWish;
    Mound currentMound = null;
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
    @Autowired
    private WishManager wishManager;
    BaseLog wishBelong;
    @Autowired
    private FlowerManager flowerManager;
    @Autowired
    private MoundManager moundManager;
    @Autowired
    private StoneManager stoneManager;
    private DashboardModel plainBoard;
    @Autowired
    private CommonContext areaContext;
    boolean needBackToHistory = false;
    String mostRecentHistory = "";
    @Autowired
    private AreaManager areaManager;
    private Wish currentWish;
    List hillockMenuWishes;

    public void addSymbolAge(HeartSymbol symbol) {
        if (symbol == null) {
            return;
        }
        int age = symbol.getAge();
        symbol.setAge(age + 1);
        persistHeartSymbol(symbol);
    }

    private void persistHeartSymbol(HeartSymbol symbol) {
// todo recover
//        areaManager._getSession().saveOrUpdate(symbol);
    }

    public String getHeartSymbolStyle(HeartSymbol symbol) throws Exception {

        return HeartSymbolResolver.resolveStyle(symbol);
    }

    public void editStone(Stone stone) {
        this.newStone = stone;
        enterEditMode(hillock);
    }

    public void enterEditMode(Place wrapper) {
        this.newStone = new Stone();
        wrapper.setEditMode(true);
    }

    public void deleteStone(Stone stone) {
        stoneManager.remove(stone);
    }

    public void selectCurrentWish(SelectEvent event) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Wish wish = wishManager.get(Long.valueOf(((String) event.getObject())));
        currentWish = wish;
    }

    public String getToolHighlightStyle(Mound mound, int toolIndex) {
        if (toolIndex == mound.getToolIndex()) {
            return "background-color:yellow";
        }
        return "";
    }

    public void firePray(Wish wish) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Pray> prays = wish.getPrays();
        Pray pray = new Pray();
        prays.add(pray);
        areaManager._getSession().save(pray);
        wishManager.saveOrUpdate(wish);
    }

    public String genMoundContent(Mound mound) {
        StringBuffer sb = new StringBuffer();
        List<BaseLog> targets = mound.getTargets();
        if (targets == null) {
            return "";
        }
        for (BaseLog baseLog : targets) {
            if (baseLog == null) {
                continue;
            }
            sb.append(baseLog.getId());
            sb.append("|");
            String content = baseLog.getContent();
            sb.append(beautify(content));
            sb.append("<br/>");
        }
        return sb.toString();
    }

    private String beautify(String content) {
        if (content == null) {
            return "";
        }
        if (content.length() > Constants.STRING_BEAUTIFY_LENGTH) {
            return content.substring(0, Constants.STRING_BEAUTIFY_LENGTH) + "...";
        }
        else {
            return content;
        }
    }

    public void backToViewMode(Place wrapper) throws IOException {
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

    public void onDialogChooseMoundTargetReturn(SelectEvent event) {
    }

    public void doDialogChooseMoundTargetClose(TreeNode[] selectedMoundTargetNodes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<BaseLog> targets = new ArrayList<>();
        if (selectedMoundTargetNodes == null) {
            targets = null;
        }
        else {
            for (TreeNode selectedMoundTargetNode : selectedMoundTargetNodes) {
                targets.add((BaseLog) selectedMoundTargetNode.getData());
            }
        }
        currentMound.setTargets(targets);
        currentMound.setCreator(areaContext.getCreator());
        currentMound.setCreateTime(new Date());
        saveOrUpdate(currentMound);
        RequestContext.getCurrentInstance().closeDialog(currentMound.getTargets());
    }

    public void saveOrUpdate(Mound mound) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        moundManager.saveOrUpdate(mound);
    }

    public void alterToChooseTargetThenInvoke(Mound mound) {
        alterMoundToolIndex(mound, Constants.TOOL_INDEX_MOUND_CHOOSE_TARGET);
        addMinusBuryCountOrChooseTarget(mound);
    }

    public void alterMoundToolIndex(Mound mound, int toolIndex) {
        mound.setToolIndex(toolIndex);
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
            currentMound = mound;
            RequestContext.getCurrentInstance().openDialog("/main/misc/plain/chooseMoundTarget.xhtml");
            return;
        }
        ;
        if (needPersist) {
            persistMoundBury(mound);
        }
    }

    private void persistMoundBury(Mound mound) {
        List<BaseLog> targets = moundManager.getTargets(mound);
        if (targets == null) {
        }
        else {
            for (BaseLog baseLog : targets) {
                baseLog.setBuryDepth(mound.getBuryDepth());
                areaManager.updateBaseObj(baseLog);
            }
        }
        areaManager.updateBaseObj(mound);
    }

    public boolean hasNoNextPage(UIData uidata) {
        int currentPage = uidata.getPage();
        int pageCount = uidata.getPageCount();
        boolean disabled = currentPage == pageCount - 1 || currentPage == 0 && pageCount == 0;
        return disabled;
    }

    public boolean hasNoPrevPage(UIData uidata) {
        boolean disabled = uidata.getPage() == 0;
        return disabled;
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

// value
        if (newWave == null) {
            newWave = new Wave();
        }

        if (newFlower == null) {
            newFlower = new Flower();
        }

        if (newStone == null) {
            newStone = new Stone();
        }
        if (newMound == null) {
            newMound = new Mound();
        }

        if (newWave.getHeartSymbol() == null) {
            HeartSymbol heartSymbol = new HeartSymbol();
            heartSymbol.setKind(HeartSymbol.Kind.wave);
            heartSymbol.setAge(0);
            newWave.setHeartSymbol(heartSymbol);
        }

        if (newFlower.getHeartSymbol() == null) {
            HeartSymbol heartSymbol = new HeartSymbol();
            heartSymbol.setKind(HeartSymbol.Kind.flower);
            heartSymbol.setAge(0);
            newFlower.setHeartSymbol(heartSymbol);
        }
        if (newStone.getHeartSymbol() == null) {
            HeartSymbol heartSymbol = new HeartSymbol();
            newStone.setHeartSymbol(heartSymbol);
        }

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

    private void genericSave(BaseLog base, GenericManager manager, Place place) {
        base.setCreator(areaContext.getCreator());
        base.setCreateTime(new Date());
        Serializable id = manager.save(base);
        place.setCurrentIndex(0);
        place.setEditMode(false);
        try {
            handleRedirHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFlower(Flower flower) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        genericSave(flower, flowerManager, swamp);
    }

    public void addOrUpdateStone(Stone stone) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        genericSaveOrUpdate(stone, stoneManager, hillock);
    }

    private void genericSaveOrUpdate(BaseLog base, GenericManager manager, Place place) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        base.setCreator(areaContext.getCreator());
        base.setCreateTime(new Date());
        manager.saveOrUpdate(base);
        place.setCurrentIndex(0);
        place.setEditMode(false);
        try {
            handleRedirHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.newWish = new Wish();
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

    public void setMenuedWishes() {

        List<Wish> menuedWishes = wishManager.getMenuedWishes(areaContext.getCreator());
        hillockMenuWishes = menuedWishes;

//		if (menuedWishes == null) {
//			return;
//		}

//		ArrayList arrayList = new ArrayList();
//		for ( Wish w : menuedWishes) {
//			SelectItem selectItem = new SelectItem();
//			selectItem.setLabel(String.valueOf(w.getId()));
//			selectItem.setValue(w.getId());
//			arrayList.add(selectItem);
//		}
//		hillockMenuWishes=arrayList ;

    }

    //getter and setter
    public Wish getCurrentWish() {
        return currentWish;
    }

    public void setCurrentWish(Wish currentWish) {
        this.currentWish = currentWish;
    }

    public List<SelectItem> getHillockMenuWishes() {
        return hillockMenuWishes;
    }

    public void setHillockMenuWishes(List hillockMenuWishes) {
        this.hillockMenuWishes = hillockMenuWishes;
    }

    public Mound getCurrentMound() {
        return currentMound;
    }

    public void setCurrentMound(Mound currentMound) {
        this.currentMound = currentMound;
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

    public Wish getNewWish() {
        return newWish;
    }

    public void setNewWish(Wish newWish) {
        this.newWish = newWish;
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

    public Flower getNewFlower() {
        return newFlower;
    }

    public void setNewFlower(Flower newFlower) {
        this.newFlower = newFlower;
    }

    public Mound getNewMound() {
        return newMound;
    }

    public void setNewMound(Mound newMound) {
        this.newMound = newMound;
    }

    public Stone getNewStone() {
        return newStone;
    }

    public void setNewStone(Stone newStone) {
        this.newStone = newStone;
    }

    public BaseLog getWishBelong() {
        return wishBelong;
    }

    public void setWishBelong(BaseLog wishBelong) {
        this.wishBelong = wishBelong;
    }

    public Wave getNewWave() {
        return newWave;
    }

    public void setNewWave(Wave newWave) {
        this.newWave = newWave;
    }

    public Sea getSea() {
        return sea;
    }

    public void setSea(Sea sea) {
        this.sea = sea;
    }
}

