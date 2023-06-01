package lk.ijse.project_wineverse.bo.custom.impl;

import lk.ijse.project_wineverse.bo.custom.EventBO;
import lk.ijse.project_wineverse.dao.DAOFactory;
import lk.ijse.project_wineverse.dao.custom.EventDAO;
import lk.ijse.project_wineverse.dto.EventDTO;
import lk.ijse.project_wineverse.entity.Event;
import lk.ijse.project_wineverse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventBOImpl implements EventBO {

    EventDAO eventDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EVENT);

    public List<String> loadIds() throws SQLException {
        return eventDAO.loadIds();
    }

    public boolean saveEvent(EventDTO dto) throws SQLException, ClassNotFoundException {
        return eventDAO.save(new Event(dto.getEventid(),dto.getEventname(),dto.getEventtype(),dto.getEventdate(),dto.getEventtime(),dto.getEmpid()));
    }

    public ArrayList<EventDTO> getAllEvents() throws SQLException {
        ArrayList<Event> all = eventDAO.getAll();
        ArrayList<EventDTO> arrayList = new ArrayList<>();

        for (Event e : all) {
            arrayList.add(new EventDTO(e.getEvent_id(),e.getEvent_name(),e.getEvent_type(),e.getEvent_date(),e.getEvent_time(),e.getEmp_id()));
        }
        return arrayList;
    }

    public boolean updateEvent(EventDTO dto) throws SQLException {
        return eventDAO.update(new Event(dto.getEventid(),dto.getEventname(),dto.getEventtype(),dto.getEventdate(),dto.getEventtime(),dto.getEmpid()));
    }

    public boolean deleteEvent(String id) throws SQLException {
        return eventDAO.delete(id);
    }

    public EventDTO findBy(String id) throws SQLException {
        Event event = eventDAO.findBy(id);
        return new EventDTO(event.getEvent_id(),event.getEvent_name(),event.getEvent_type(),event.getEvent_date(),event.getEvent_time(),event.getEmp_id());
    }
}
