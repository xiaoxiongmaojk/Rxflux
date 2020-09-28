package cn.co.demo.rxflux.action;

import cn.co.demo.rxflux.repository.entity.ApiGetSampleEntity;

public class ApiSampleAction {

  public static class OnApiGetSample extends Action<ApiGetSampleEntity> {
    public static final String TYPE = "ApiSampleAction.OnApiGetSample";

    public OnApiGetSample(ApiGetSampleEntity data) {
      super(data);
    }

    @Override public String getType() {
      return TYPE;
    }
  }

}
