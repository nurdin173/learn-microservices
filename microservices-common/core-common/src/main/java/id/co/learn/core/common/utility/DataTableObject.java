package id.co.learn.core.common.utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter @Setter
public class DataTableObject<T> {
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private String sEcho;
    private String sColumns;
    private String sSortBy;
    private String sSortOrder;
    private List<T> aaData;

    public DataTableObject(String sEcho, String sColumns) {
        this.sEcho = sEcho;
        this.sColumns = sColumns;
    }

    public static int getPageFromStartAndLength(int iDisplayStart, int iDisplayLength) {
        int expectedCalc = 0;
        if (iDisplayStart > 1) {
            expectedCalc = iDisplayStart / iDisplayLength;
        }

        return expectedCalc;
    }

    public DataTableObject convertToDataTable(Page<T> pageable) {
        this.setITotalRecords(Math.toIntExact(pageable.getTotalElements()));
        this.setAaData(pageable.getContent());
        this.setITotalRecords((int) pageable.getTotalElements());

        return this;
    }
}
