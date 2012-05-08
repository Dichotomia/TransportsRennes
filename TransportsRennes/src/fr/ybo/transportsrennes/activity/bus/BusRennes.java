/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.ybo.transportsrennes.activity.bus;

import java.util.List;

import android.content.Intent;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;

import fr.ybo.transportscommun.activity.commun.BaseActivity.BaseListActivity;
import fr.ybo.transportscommun.donnees.modele.Ligne;
import fr.ybo.transportsrennes.R;
import fr.ybo.transportsrennes.adapters.bus.LigneAdapter;
import fr.ybo.transportsrennes.application.TransportsRennesApplication;

/**
 * Activité affichant les lignes de bus..
 *
 * @author ybonnel
 */
@EActivity(R.layout.bus)
public class BusRennes extends BaseListActivity {
	@ItemClick
	void listItemClicked(Ligne ligne) {
		Intent intent = new Intent(BusRennes.this, ListArret.class);
		intent.putExtra("ligne", ligne);
		startActivity(intent);
	}

	@AfterViews
	void afterViews() {
		getActivityHelper().setupActionBar(R.menu.default_menu_items, R.menu.holo_default_menu_items);
        constructionListe();
    }

	private void constructionListe() {
		List<Ligne> lignes = TransportsRennesApplication.getDataBaseHelper().select(new Ligne(), "ordre");
		setListAdapter(new LigneAdapter(this, lignes));
		ListView lv = getListView();
		lv.setFastScrollEnabled(true);
		lv.setTextFilterEnabled(true);
	}
}
