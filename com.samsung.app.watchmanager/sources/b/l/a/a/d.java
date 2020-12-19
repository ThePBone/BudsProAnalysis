package b.l.a.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.core.content.a.i;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class d extends i implements b {

    /* renamed from: b  reason: collision with root package name */
    private a f1487b;

    /* renamed from: c  reason: collision with root package name */
    private Context f1488c;

    /* renamed from: d  reason: collision with root package name */
    private ArgbEvaluator f1489d;
    private Animator.AnimatorListener e;
    ArrayList<Object> f;
    final Drawable.Callback g;

    /* access modifiers changed from: private */
    public static class a extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        int f1490a;

        /* renamed from: b  reason: collision with root package name */
        k f1491b;

        /* renamed from: c  reason: collision with root package name */
        AnimatorSet f1492c;

        /* renamed from: d  reason: collision with root package name */
        ArrayList<Animator> f1493d;
        b.c.b<Animator, String> e;

        public a(Context context, a aVar, Drawable.Callback callback, Resources resources) {
            if (aVar != null) {
                this.f1490a = aVar.f1490a;
                k kVar = aVar.f1491b;
                if (kVar != null) {
                    Drawable.ConstantState constantState = kVar.getConstantState();
                    this.f1491b = (k) (resources != null ? constantState.newDrawable(resources) : constantState.newDrawable());
                    k kVar2 = this.f1491b;
                    kVar2.mutate();
                    this.f1491b = kVar2;
                    this.f1491b.setCallback(callback);
                    this.f1491b.setBounds(aVar.f1491b.getBounds());
                    this.f1491b.a(false);
                }
                ArrayList<Animator> arrayList = aVar.f1493d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f1493d = new ArrayList<>(size);
                    this.e = new b.c.b<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = aVar.f1493d.get(i);
                        Animator clone = animator.clone();
                        String str = aVar.e.get(animator);
                        clone.setTarget(this.f1491b.a(str));
                        this.f1493d.add(clone);
                        this.e.put(clone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f1492c == null) {
                this.f1492c = new AnimatorSet();
            }
            this.f1492c.playTogether(this.f1493d);
        }

        public int getChangingConfigurations() {
            return this.f1490a;
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    private static class b extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        private final Drawable.ConstantState f1494a;

        public b(Drawable.ConstantState constantState) {
            this.f1494a = constantState;
        }

        public boolean canApplyTheme() {
            return this.f1494a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.f1494a.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            d dVar = new d();
            dVar.f1499a = this.f1494a.newDrawable();
            dVar.f1499a.setCallback(dVar.g);
            return dVar;
        }

        public Drawable newDrawable(Resources resources) {
            d dVar = new d();
            dVar.f1499a = this.f1494a.newDrawable(resources);
            dVar.f1499a.setCallback(dVar.g);
            return dVar;
        }

        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            d dVar = new d();
            dVar.f1499a = this.f1494a.newDrawable(resources, theme);
            dVar.f1499a.setCallback(dVar.g);
            return dVar;
        }
    }

    d() {
        this(null, null, null);
    }

    private d(Context context) {
        this(context, null, null);
    }

    private d(Context context, a aVar, Resources resources) {
        this.f1489d = null;
        this.e = null;
        this.f = null;
        this.g = new c(this);
        this.f1488c = context;
        if (aVar != null) {
            this.f1487b = aVar;
        } else {
            this.f1487b = new a(context, aVar, this.g, resources);
        }
    }

    public static d a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        d dVar = new d(context);
        dVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return dVar;
    }

    private void a(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                a(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f1489d == null) {
                    this.f1489d = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f1489d);
            }
        }
    }

    private void a(String str, Animator animator) {
        animator.setTarget(this.f1487b.f1491b.a(str));
        if (Build.VERSION.SDK_INT < 21) {
            a(animator);
        }
        a aVar = this.f1487b;
        if (aVar.f1493d == null) {
            aVar.f1493d = new ArrayList<>();
            this.f1487b.e = new b.c.b<>();
        }
        this.f1487b.f1493d.add(animator);
        this.f1487b.e.put(animator, str);
    }

    @Override // b.l.a.a.i
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, theme);
        }
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.a(drawable);
        }
        return false;
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f1487b.f1491b.draw(canvas);
        if (this.f1487b.f1492c.isStarted()) {
            invalidateSelf();
        }
    }

    public int getAlpha() {
        Drawable drawable = this.f1499a;
        return drawable != null ? androidx.core.graphics.drawable.a.b(drawable) : this.f1487b.f1491b.getAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f1487b.f1490a;
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f1499a;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new b(drawable.getConstantState());
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f1487b.f1491b.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f1487b.f1491b.getIntrinsicWidth();
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.getOpacity() : this.f1487b.f1491b.getOpacity();
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray obtainAttributes;
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    obtainAttributes = i.a(resources, theme, attributeSet, a.e);
                    int resourceId = obtainAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        k a2 = k.a(resources, resourceId, theme);
                        a2.a(false);
                        a2.setCallback(this.g);
                        k kVar = this.f1487b.f1491b;
                        if (kVar != null) {
                            kVar.setCallback(null);
                        }
                        this.f1487b.f1491b = a2;
                    }
                } else if ("target".equals(name)) {
                    obtainAttributes = resources.obtainAttributes(attributeSet, a.f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f1488c;
                        if (context != null) {
                            a(string, f.a(context, resourceId2));
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                } else {
                    continue;
                }
                obtainAttributes.recycle();
            }
            eventType = xmlPullParser.next();
        }
        this.f1487b.a();
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.f1499a;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f1487b.f1491b.isAutoMirrored();
    }

    public boolean isRunning() {
        Drawable drawable = this.f1499a;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f1487b.f1492c.isRunning();
    }

    public boolean isStateful() {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.isStateful() : this.f1487b.f1491b.isStateful();
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f1487b.f1491b.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    @Override // b.l.a.a.i
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.setLevel(i) : this.f1487b.f1491b.setLevel(i);
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1499a;
        return drawable != null ? drawable.setState(iArr) : this.f1487b.f1491b.setState(iArr);
    }

    public void setAlpha(int i) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f1487b.f1491b.setAlpha(i);
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, z);
        } else {
            this.f1487b.f1491b.setAutoMirrored(z);
        }
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f1487b.f1491b.setColorFilter(colorFilter);
        }
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // b.l.a.a.i
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.b(drawable, i);
        } else {
            this.f1487b.f1491b.setTint(i);
        }
    }

    @Override // androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, colorStateList);
        } else {
            this.f1487b.f1491b.setTintList(colorStateList);
        }
    }

    @Override // androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, mode);
        } else {
            this.f1487b.f1491b.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f1487b.f1491b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public void start() {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else if (!this.f1487b.f1492c.isStarted()) {
            this.f1487b.f1492c.start();
            invalidateSelf();
        }
    }

    public void stop() {
        Drawable drawable = this.f1499a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f1487b.f1492c.end();
        }
    }
}
