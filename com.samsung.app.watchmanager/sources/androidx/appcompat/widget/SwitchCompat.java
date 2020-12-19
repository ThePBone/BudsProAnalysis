package androidx.appcompat.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.core.widget.m;
import b.a.a;
import b.a.j;
import b.e.g.t;
import com.samsung.android.app.twatchmanager.update.SAGUIDHelper;

public class SwitchCompat extends CompoundButton {

    /* renamed from: a  reason: collision with root package name */
    private static final Property<SwitchCompat, Float> f417a = new ca(Float.class, "thumbPos");

    /* renamed from: b  reason: collision with root package name */
    private static final int[] f418b = {16842912};
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final TextPaint H;
    private ColorStateList I;
    private Layout J;
    private Layout K;
    private TransformationMethod L;
    ObjectAnimator M;
    private final Rect N;

    /* renamed from: c  reason: collision with root package name */
    private Drawable f419c;

    /* renamed from: d  reason: collision with root package name */
    private ColorStateList f420d;
    private PorterDuff.Mode e;
    private boolean f;
    private boolean g;
    private Drawable h;
    private ColorStateList i;
    private PorterDuff.Mode j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private CharSequence q;
    private CharSequence r;
    private boolean s;
    private int t;
    private int u;
    private float v;
    private float w;
    private VelocityTracker x;
    private int y;
    float z;

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f420d = null;
        this.e = null;
        this.f = false;
        this.g = false;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.x = VelocityTracker.obtain();
        this.N = new Rect();
        this.H = new TextPaint(1);
        Resources resources = getResources();
        this.H.density = resources.getDisplayMetrics().density;
        ia a2 = ia.a(context, attributeSet, j.SwitchCompat, i2, 0);
        this.f419c = a2.b(j.SwitchCompat_android_thumb);
        Drawable drawable = this.f419c;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.h = a2.b(j.SwitchCompat_track);
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        this.q = a2.e(j.SwitchCompat_android_textOn);
        this.r = a2.e(j.SwitchCompat_android_textOff);
        this.s = a2.a(j.SwitchCompat_showText, true);
        this.m = a2.c(j.SwitchCompat_thumbTextPadding, 0);
        this.n = a2.c(j.SwitchCompat_switchMinWidth, 0);
        this.o = a2.c(j.SwitchCompat_switchPadding, 0);
        this.p = a2.a(j.SwitchCompat_splitTrack, false);
        ColorStateList a3 = a2.a(j.SwitchCompat_thumbTint);
        if (a3 != null) {
            this.f420d = a3;
            this.f = true;
        }
        PorterDuff.Mode a4 = E.a(a2.d(j.SwitchCompat_thumbTintMode, -1), null);
        if (this.e != a4) {
            this.e = a4;
            this.g = true;
        }
        if (this.f || this.g) {
            a();
        }
        ColorStateList a5 = a2.a(j.SwitchCompat_trackTint);
        if (a5 != null) {
            this.i = a5;
            this.k = true;
        }
        PorterDuff.Mode a6 = E.a(a2.d(j.SwitchCompat_trackTintMode, -1), null);
        if (this.j != a6) {
            this.j = a6;
            this.l = true;
        }
        if (this.k || this.l) {
            b();
        }
        int g2 = a2.g(j.SwitchCompat_switchTextAppearance, 0);
        if (g2 != 0) {
            setSwitchTextAppearance(context, g2);
        }
        a2.a();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.u = viewConfiguration.getScaledTouchSlop();
        this.y = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    private static float a(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    private Layout a(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.L;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        TextPaint textPaint = this.H;
        return new StaticLayout(charSequence, textPaint, charSequence != null ? (int) Math.ceil((double) Layout.getDesiredWidth(charSequence, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private void a() {
        if (this.f419c == null) {
            return;
        }
        if (this.f || this.g) {
            this.f419c = this.f419c.mutate();
            if (this.f) {
                androidx.core.graphics.drawable.a.a(this.f419c, this.f420d);
            }
            if (this.g) {
                androidx.core.graphics.drawable.a.a(this.f419c, this.e);
            }
            if (this.f419c.isStateful()) {
                this.f419c.setState(getDrawableState());
            }
        }
    }

    private void a(int i2, int i3) {
        setSwitchTypeface(i2 != 1 ? i2 != 2 ? i2 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i3);
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void a(boolean z2) {
        this.M = ObjectAnimator.ofFloat(this, f417a, z2 ? 1.0f : 0.0f);
        this.M.setDuration(250L);
        if (Build.VERSION.SDK_INT >= 18) {
            this.M.setAutoCancel(true);
        }
        this.M.start();
    }

    private boolean a(float f2, float f3) {
        if (this.f419c == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f419c.getPadding(this.N);
        int i2 = this.E;
        int i3 = this.u;
        int i4 = i2 - i3;
        int i5 = (this.D + thumbOffset) - i3;
        Rect rect = this.N;
        return f2 > ((float) i5) && f2 < ((float) ((((this.C + i5) + rect.left) + rect.right) + i3)) && f3 > ((float) i4) && f3 < ((float) (this.G + i3));
    }

    private void b() {
        if (this.h == null) {
            return;
        }
        if (this.k || this.l) {
            this.h = this.h.mutate();
            if (this.k) {
                androidx.core.graphics.drawable.a.a(this.h, this.i);
            }
            if (this.l) {
                androidx.core.graphics.drawable.a.a(this.h, this.j);
            }
            if (this.h.isStateful()) {
                this.h.setState(getDrawableState());
            }
        }
    }

    private void b(MotionEvent motionEvent) {
        boolean z2;
        this.t = 0;
        boolean z3 = true;
        boolean z4 = motionEvent.getAction() == 1 && isEnabled();
        boolean isChecked = isChecked();
        if (z4) {
            this.x.computeCurrentVelocity(SAGUIDHelper.GUID_REQUEST_ID);
            float xVelocity = this.x.getXVelocity();
            if (Math.abs(xVelocity) > ((float) this.y)) {
                if (!wa.a(this) ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                    z3 = false;
                }
                z2 = z3;
            } else {
                z2 = getTargetCheckedState();
            }
        } else {
            z2 = isChecked;
        }
        if (z2 != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z2);
        a(motionEvent);
    }

    private void c() {
        ObjectAnimator objectAnimator = this.M;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.z > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((wa.a(this) ? 1.0f - this.z : this.z) * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.h;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.N;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f419c;
        Rect c2 = drawable2 != null ? E.c(drawable2) : E.f351a;
        return ((((this.A - this.C) - rect.left) - rect.right) - c2.left) - c2.right;
    }

    public void draw(Canvas canvas) {
        int i2;
        int i3;
        Rect rect = this.N;
        int i4 = this.D;
        int i5 = this.E;
        int i6 = this.F;
        int i7 = this.G;
        int thumbOffset = getThumbOffset() + i4;
        Drawable drawable = this.f419c;
        Rect c2 = drawable != null ? E.c(drawable) : E.f351a;
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i8 = rect.left;
            thumbOffset += i8;
            if (c2 != null) {
                int i9 = c2.left;
                if (i9 > i8) {
                    i4 += i9 - i8;
                }
                int i10 = c2.top;
                int i11 = rect.top;
                i2 = i10 > i11 ? (i10 - i11) + i5 : i5;
                int i12 = c2.right;
                int i13 = rect.right;
                if (i12 > i13) {
                    i6 -= i12 - i13;
                }
                int i14 = c2.bottom;
                int i15 = rect.bottom;
                if (i14 > i15) {
                    i3 = i7 - (i14 - i15);
                    this.h.setBounds(i4, i2, i6, i3);
                }
            } else {
                i2 = i5;
            }
            i3 = i7;
            this.h.setBounds(i4, i2, i6, i3);
        }
        Drawable drawable3 = this.f419c;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i16 = thumbOffset - rect.left;
            int i17 = thumbOffset + this.C + rect.right;
            this.f419c.setBounds(i16, i5, i17, i7);
            Drawable background = getBackground();
            if (background != null) {
                androidx.core.graphics.drawable.a.a(background, i16, i5, i17, i7);
            }
        }
        super.draw(canvas);
    }

    public void drawableHotspotChanged(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        Drawable drawable = this.f419c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, f2, f3);
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            androidx.core.graphics.drawable.a.a(drawable2, f2, f3);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f419c;
        boolean z2 = false;
        if (drawable != null && drawable.isStateful()) {
            z2 = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null && drawable2.isStateful()) {
            z2 |= drawable2.setState(drawableState);
        }
        if (z2) {
            invalidate();
        }
    }

    public int getCompoundPaddingLeft() {
        if (!wa.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.o : compoundPaddingLeft;
    }

    public int getCompoundPaddingRight() {
        if (wa.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.o : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.s;
    }

    public boolean getSplitTrack() {
        return this.p;
    }

    public int getSwitchMinWidth() {
        return this.n;
    }

    public int getSwitchPadding() {
        return this.o;
    }

    public CharSequence getTextOff() {
        return this.r;
    }

    public CharSequence getTextOn() {
        return this.q;
    }

    public Drawable getThumbDrawable() {
        return this.f419c;
    }

    public int getThumbTextPadding() {
        return this.m;
    }

    public ColorStateList getThumbTintList() {
        return this.f420d;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.e;
    }

    public Drawable getTrackDrawable() {
        return this.h;
    }

    public ColorStateList getTrackTintList() {
        return this.i;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.j;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f419c;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.M;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.M.end();
            this.M = null;
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            CompoundButton.mergeDrawableStates(onCreateDrawableState, f418b);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        Rect rect = this.N;
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i3 = this.E;
        int i4 = this.G;
        int i5 = i3 + rect.top;
        int i6 = i4 - rect.bottom;
        Drawable drawable2 = this.f419c;
        if (drawable != null) {
            if (!this.p || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect c2 = E.c(drawable2);
                drawable2.copyBounds(rect);
                rect.left += c2.left;
                rect.right -= c2.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.J : this.K;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.I;
            if (colorStateList != null) {
                this.H.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                i2 = bounds.left + bounds.right;
            } else {
                i2 = getWidth();
            }
            canvas.translate((float) ((i2 / 2) - (layout.getWidth() / 2)), (float) (((i5 + i6) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.q : this.r;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        super.onLayout(z2, i2, i3, i4, i5);
        int i12 = 0;
        if (this.f419c != null) {
            Rect rect = this.N;
            Drawable drawable = this.h;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect c2 = E.c(this.f419c);
            i6 = Math.max(0, c2.left - rect.left);
            i12 = Math.max(0, c2.right - rect.right);
        } else {
            i6 = 0;
        }
        if (wa.a(this)) {
            i8 = getPaddingLeft() + i6;
            i7 = ((this.A + i8) - i6) - i12;
        } else {
            i7 = (getWidth() - getPaddingRight()) - i12;
            i8 = (i7 - this.A) + i6 + i12;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            i11 = this.B;
            i10 = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (i11 / 2);
        } else if (gravity != 80) {
            i10 = getPaddingTop();
            i11 = this.B;
        } else {
            i9 = getHeight() - getPaddingBottom();
            i10 = i9 - this.B;
            this.D = i8;
            this.E = i10;
            this.G = i9;
            this.F = i7;
        }
        i9 = i11 + i10;
        this.D = i8;
        this.E = i10;
        this.G = i9;
        this.F = i7;
    }

    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        if (this.s) {
            if (this.J == null) {
                this.J = a(this.q);
            }
            if (this.K == null) {
                this.K = a(this.r);
            }
        }
        Rect rect = this.N;
        Drawable drawable = this.f419c;
        int i6 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i5 = (this.f419c.getIntrinsicWidth() - rect.left) - rect.right;
            i4 = this.f419c.getIntrinsicHeight();
        } else {
            i5 = 0;
            i4 = 0;
        }
        this.C = Math.max(this.s ? Math.max(this.J.getWidth(), this.K.getWidth()) + (this.m * 2) : 0, i5);
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i6 = this.h.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i7 = rect.left;
        int i8 = rect.right;
        Drawable drawable3 = this.f419c;
        if (drawable3 != null) {
            Rect c2 = E.c(drawable3);
            i7 = Math.max(i7, c2.left);
            i8 = Math.max(i8, c2.right);
        }
        int max = Math.max(this.n, (this.C * 2) + i7 + i8);
        int max2 = Math.max(i6, i4);
        this.A = max;
        this.B = max2;
        super.onMeasure(i2, i3);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.q : this.r;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r0 != 3) goto L_0x00bb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setChecked(boolean z2) {
        super.setChecked(z2);
        boolean isChecked = isChecked();
        if (getWindowToken() == null || !t.w(this)) {
            c();
            setThumbPosition(isChecked ? 1.0f : 0.0f);
            return;
        }
        a(isChecked);
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(m.a(this, callback));
    }

    public void setShowText(boolean z2) {
        if (this.s != z2) {
            this.s = z2;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z2) {
        this.p = z2;
        invalidate();
    }

    public void setSwitchMinWidth(int i2) {
        this.n = i2;
        requestLayout();
    }

    public void setSwitchPadding(int i2) {
        this.o = i2;
        requestLayout();
    }

    public void setSwitchTextAppearance(Context context, int i2) {
        ia a2 = ia.a(context, i2, j.TextAppearance);
        ColorStateList a3 = a2.a(j.TextAppearance_android_textColor);
        if (a3 == null) {
            a3 = getTextColors();
        }
        this.I = a3;
        int c2 = a2.c(j.TextAppearance_android_textSize, 0);
        if (c2 != 0) {
            float f2 = (float) c2;
            if (f2 != this.H.getTextSize()) {
                this.H.setTextSize(f2);
                requestLayout();
            }
        }
        a(a2.d(j.TextAppearance_android_typeface, -1), a2.d(j.TextAppearance_android_textStyle, -1));
        this.L = a2.a(j.TextAppearance_textAllCaps, false) ? new b.a.c.a(getContext()) : null;
        a2.a();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchTypeface(Typeface typeface, int i2) {
        float f2 = 0.0f;
        boolean z2 = false;
        if (i2 > 0) {
            Typeface defaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i2) : Typeface.create(typeface, i2);
            setSwitchTypeface(defaultFromStyle);
            int style = ((defaultFromStyle != null ? defaultFromStyle.getStyle() : 0) ^ -1) & i2;
            TextPaint textPaint = this.H;
            if ((style & 1) != 0) {
                z2 = true;
            }
            textPaint.setFakeBoldText(z2);
            TextPaint textPaint2 = this.H;
            if ((style & 2) != 0) {
                f2 = -0.25f;
            }
            textPaint2.setTextSkewX(f2);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setTextOff(CharSequence charSequence) {
        this.r = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.q = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f419c;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f419c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void setThumbPosition(float f2) {
        this.z = f2;
        invalidate();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(b.a.a.a.a.b(getContext(), i2));
    }

    public void setThumbTextPadding(int i2) {
        this.m = i2;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f420d = colorStateList;
        this.f = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.e = mode;
        this.g = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(b.a.a.a.a.b(getContext(), i2));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.i = colorStateList;
        this.k = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.j = mode;
        this.l = true;
        b();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f419c || drawable == this.h;
    }
}
