package com.accessorydm.ui.lastupdate;

import android.os.Bundle;
import android.view.ViewStub;
import com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity;
import com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenContract;
import com.accessorydm.ui.fullscreen.content.MiddleContentView;
import com.accessorydm.ui.fullscreen.content.TopContentView;
import com.accessorydm.ui.lastupdate.XUILastUpdateContract;
import com.samsung.android.fotaprovider.log.Log;

public class XUILastUpdateActivity extends XUIBaseFullscreenActivity implements XUILastUpdateContract.View {
    private MiddleContentView.WithoutCaution middleContentView = null;
    private XUILastUpdateContract.Presenter presenter = null;
    private TopContentView.Guide topContentView = null;

    @Override // com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity
    public void xuiGenerateBottomLayout(ViewStub viewStub) {
    }

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.I("");
        this.presenter = new XUILastUpdatePresenter(this);
        this.presenter.onCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity
    public XUIBaseFullscreenContract.Presenter getPresenter() {
        return this.presenter;
    }

    @Override // com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity
    public void xuiGenerateTopContentLayout(ViewStub viewStub) {
        this.topContentView = new TopContentView.Guide(this, viewStub);
    }

    @Override // com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity
    public void xuiGenerateMiddleContentLayout(ViewStub viewStub) {
        this.middleContentView = new MiddleContentView.WithoutCaution(this, viewStub);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, com.accessorydm.ui.fullscreen.basefullscreen.XUIBaseFullscreenActivity
    public void onDestroy() {
        Log.I("");
        super.onDestroy();
    }

    @Override // com.accessorydm.ui.lastupdate.XUILastUpdateContract.View
    public TopContentView.Guide getTopContentView() {
        return this.topContentView;
    }

    @Override // com.accessorydm.ui.lastupdate.XUILastUpdateContract.View
    public MiddleContentView.WithoutCaution getMiddleContentView() {
        return this.middleContentView;
    }
}
