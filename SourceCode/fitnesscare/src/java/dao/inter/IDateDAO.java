
package dao.inter;

import model.DateObject;

/**
 *
 * @author ThinkPro
 */
public interface IDateDAO {

    DateObject get7day();

     int countDayByStartEnd(String start, String end);

}
