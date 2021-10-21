package com.example.myapplication;

import java.util.List;

public class NewEvent {
    String Event_id, groups, eventTitle,
            eventDescription, eventLocation, Event_date, event_image;
    List<User> users;

    public NewEvent() {
    }



    public NewEvent(String event_id, String groups, String eventTitle, String eventDescription,
                    String eventLocation, String event_date, List<User> users, String event_image) {
        Event_id = event_id;
        this.groups = groups;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.Event_date = event_date;
        this.users = users;
        this.event_image = event_image;

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getEvent_id() {
        return Event_id;
    }

    public void setEvent_id(String event_id) {
        Event_id = event_id;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {

        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEvent_date() {
        return Event_date;
    }

    public void setEvent_date(String event_date) {
        Event_date = event_date;
    }

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }
}
