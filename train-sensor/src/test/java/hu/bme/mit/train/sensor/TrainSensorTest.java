package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorTest {
    
    TrainController mockController;
    TrainUser mockUser;
    TrainSensor sensor;

    @Before
    public void before() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockController, mockUser);
    }

    @Test
    public void MaxSpeedLimit() {
        when(mockController.getReferenceSpeed()).thenReturn(20);
        sensor.overrideSpeedLimit(-5);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void NegativeSpeedLimit() {
        when(mockController.getReferenceSpeed()).thenReturn(300);
        sensor.overrideSpeedLimit(600);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void LessThanHalfSpeedLimit() {
        when(mockController.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void OKSpeedLimit() {
        when(mockController.getReferenceSpeed()).thenReturn(250);
        sensor.overrideSpeedLimit(260);
        verify(mockUser, times(0)).setAlarmState(true);
    }
}
