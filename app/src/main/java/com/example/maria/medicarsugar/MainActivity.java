package com.example.maria.medicarsugar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maria.medicarsugar.ListaVazia.FragmentListaVazia;
import com.example.maria.medicarsugar.config_caixa.LayoutCaixaFragment;
import com.example.maria.medicarsugar.fragments.ListaMedicamentoFragment;
import com.example.maria.medicarsugar.fragments.ListaMedicamentosSuspensosFragment;
import com.example.maria.medicarsugar.fragments.ListaMedicoFragment;
import com.example.maria.medicarsugar.fragments.ListaMovimentacaoFragment;
import com.example.maria.medicarsugar.fragments.ListaReceitaFragment;
import com.example.maria.medicarsugar.modelo.Movimentacao;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Drawer result;
    private ListView listViewInicial;
    private List<Movimentacao> listarMovimentacao;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction tx = fragmentManager.beginTransaction();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewInicial = (ListView) findViewById(R.id.listViewInicio);
        listarMovimentacao = Movimentacao.listAll(Movimentacao.class);

        if(listarMovimentacao.size() == 0){
            
            tx.replace(R.id.main_frame, new ListaMedicoFragment());
            tx.commit();
        }

        /*View emptyView = getLayoutInflater().inflate(R.layout.lista_vazia, null);
        addContentView(emptyView, listViewInicial.getLayoutParams()); // You're using the same params as listView
        listViewInicial.setEmptyView(emptyView);*/



        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Inicio").withIcon(getResources().getDrawable(R.drawable.ic_heart_pulse));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Medicamento").withIcon(getResources().getDrawable(R.drawable.ic_pill));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Médico").withIcon(getResources().getDrawable(R.drawable.ic_stethoscope));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Receita").withIcon(getResources().getDrawable(R.drawable.ic_hospital));
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Medicamentos Suspensos").withIcon(getResources().getDrawable(R.drawable.ic_pill));

        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withName("Configurações da Caixa").withIcon(getResources().getDrawable(R.drawable.ic_settings));

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withRootView(R.id.drawer_layout)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withActionBarDrawerToggle(true)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                    item1,
                    item2,
                    item3,
                    item4,
                    item5,

                    new DividerDrawerItem(),

                    item6



                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction tx = fragmentManager.beginTransaction();
                        switch (position){
                            case 0:
                                //INICIO
                                //substituir o main_frame pela instancia de listaReceitaFragment
                                
                                tx.replace(R.id.main_frame, new ListaMedicoFragment());
                                tx.commit();
                                //Toast.makeText(MainActivity.this, "Onclick INICIO", Toast.LENGTH_SHORT).show();
                            break;
                            case 1:
                                //REMEDIO
                                tx = fragmentManager.beginTransaction();
                                tx.replace(R.id.main_frame, new ListaMedicamentoFragment());
                                tx.commit();
                                //Toast.makeText(MainActivity.this, "OnclickMedicamento", Toast.LENGTH_SHORT).show();
                            break;

                            case 2:
                                //MEDICO
                                tx = fragmentManager.beginTransaction();
                                tx.replace(R.id.main_frame, new ListaMedicoFragment());
                                tx.commit();
                                //Toast.makeText(MainActivity.this, "OnclickMedico", Toast.LENGTH_SHORT).show();
                            break;

                            //RECEITA
                            case 3:
                                tx.replace(R.id.main_frame, new ListaReceitaFragment());
                                tx.commit();
                                //Toast.makeText(MainActivity.this, "Onclick Receita", Toast.LENGTH_SHORT).show();
                            break;

                            //Lista de medicamentos Suspensos
                            case 5:
                                tx.replace(R.id.main_frame, new ListaMedicamentosSuspensosFragment());
                                tx.commit();
                                //Toast.makeText(MainActivity.this, "Onclick Config", Toast.LENGTH_SHORT).show();
                                Log.i("Main", "Clicou config");

                            break;

                            //CONFIGURAÇÃO DA CAIXA
                            case 6:
                                tx.replace(R.id.main_frame, new LayoutCaixaFragment());
                                tx.commit();
                                //Toast.makeText(MainActivity.this, "Onclick Config", Toast.LENGTH_SHORT).show();
                                Log.i("Main", "Clicou config");

                                break;
                        }



                        return false;
                    }
                })


                .build();

    }



}