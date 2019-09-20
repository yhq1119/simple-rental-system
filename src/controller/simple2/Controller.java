package controller.simple2;

import model.Property;
import model.Record;
import model.RentalSys;
import view.simpleView2.ViewModel2;

public class Controller {

    RentalSys rentalSys;
    ViewModel2 viewModel2;

    public Controller(RentalSys rentalSys, ViewModel2 viewModel2) {
        this.rentalSys = rentalSys;
        this.viewModel2 = viewModel2;
        init();
    }

    private void init(){
        viewModel2.getButtons()[5].setOnAction((event -> {
            String str = "";
            for (Property p:rentalSys.getProperties()){
                str = str + p.showInfo();
                for (Record record:p.getRecords()){
                    str  = str + record.showInfo();
                }
            }
            if (rentalSys.getProperties().size()<1){
                str = "No Properties";
            }
            viewModel2.getDisplay().setText(str);
        }));
    }
}
