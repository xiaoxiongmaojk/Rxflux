package cn.co.demo.rxflux.action;

import cn.co.demo.rxflux.repository.entity.room.TestDataEntity;

public class RoomSampleAction {

  public static class OnGetAllRoomSampleData extends Action<TestDataEntity> {
    public static final String TYPE = "RoomSampleAction.OnGetAllRoomSampleData";

    public OnGetAllRoomSampleData(TestDataEntity data) {
      super(data);
    }

    @Override public String getType() {
      return TYPE;
    }
  }

  public static class OnRoomSampleInsert extends Action<TestDataEntity> {
    public static final String TYPE = "RoomSampleAction.OnRoomSampleInsert";

    public OnRoomSampleInsert(TestDataEntity data) {
      super(data);
    }

    @Override public String getType() {
      return TYPE;
    }
  }
}
