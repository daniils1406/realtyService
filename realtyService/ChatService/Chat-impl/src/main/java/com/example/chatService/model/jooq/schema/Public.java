/*
 * This file is generated by jOOQ.
 */
package com.example.chatService.model.jooq.schema;


import com.example.chatService.model.jooq.schema.tables.ChatAndUser;
import com.example.chatService.model.jooq.schema.tables.ChatRoom;
import com.example.chatService.model.jooq.schema.tables.Message;
import com.example.chatService.model.jooq.schema.tables.Notification;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.chat_and_user</code>.
     */
    public final ChatAndUser CHAT_AND_USER_ENTITY = ChatAndUser.CHAT_AND_USER_ENTITY;

    /**
     * The table <code>public.chat_room</code>.
     */
    public final ChatRoom CHAT_ROOM_ENTITY = ChatRoom.CHAT_ROOM_ENTITY;

    /**
     * The table <code>public.message</code>.
     */
    public final Message MESSAGE_ENTITY = Message.MESSAGE_ENTITY;

    /**
     * The table <code>public.notification</code>.
     */
    public final Notification NOTIFICATION_ENTITY = Notification.NOTIFICATION_ENTITY;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            ChatAndUser.CHAT_AND_USER_ENTITY,
            ChatRoom.CHAT_ROOM_ENTITY,
            Message.MESSAGE_ENTITY,
            Notification.NOTIFICATION_ENTITY
        );
    }
}
