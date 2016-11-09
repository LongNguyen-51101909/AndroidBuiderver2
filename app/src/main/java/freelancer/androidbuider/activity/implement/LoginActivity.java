package freelancer.androidbuider.activity.implement;

import android.accounts.AbstractAccountAuthenticator;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import freelancer.androidbuider.R;
import freelancer.androidbuider.activity.AbstractActivity;
import freelancer.androidbuider.view.ShapeSelectorView;

/**
 * Created by nqlong on 21-Sep-16.
 */
public class LoginActivity extends AbstractActivity {
    @BindView(R.id.shapeSelector)
    ShapeSelectorView shapeSelectorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
