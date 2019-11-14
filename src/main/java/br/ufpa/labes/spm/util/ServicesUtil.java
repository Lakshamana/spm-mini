package br.ufpa.labes.spm.util;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.BranchANDCon;
import br.ufpa.labes.spm.domain.MultipleCon;

public class ServicesUtil {

  public static int horasEntre(Date inicio, Date fim) {
    DateTime i = new DateTime(inicio);
    DateTime f = new DateTime(fim);

    return Hours.hoursBetween(i, f).getHours() % 24;
  }

  public static int diasEntre(Date inicio, Date fim) {
    DateTime i = new DateTime(inicio);
    DateTime f = new DateTime(fim);

    return Days.daysBetween(i, f).getDays();
  }

  public static int minutosEntre(Date inicio, Date fim) {
    DateTime i = new DateTime(inicio);
    DateTime f = new DateTime(fim);

    return Minutes.minutesBetween(i, f).getMinutes() % 60;
  }

  public static int horasEmSegundos(int segundos) {
    return (segundos / 3600);
  }

  public static int minutosEmSegundos(int segundos) {
    return ((segundos % 3600) / 60);
  }

  public static int segundosEntre(Date inicio, Date fim) {
    DateTime i = new DateTime(inicio);
    DateTime f = new DateTime(fim);

    return Seconds.secondsBetween(i, f).getSeconds();
  }

  public static int horasDaAgenda(float time) {
    int horas = (int) ((5 * time) / 6);
    //		System.out.println("Horas: " + (5 * time) /6);
    return horas;
  }

  public static int minutosDaAgenda(float time, int horas) {
    int minutos = (int) (2.5 * (time - horas));
    //		System.out.println("Minutos: " + (time - horas));
    return minutos;
  }

  public static Activity findActivityById(List<Activity> lookupList, Long id) {
    return lookupList.stream().filter(a -> a.getId().equals(id)).findFirst().get();
  }

  public static MultipleCon findMultipleConById(List<MultipleCon> lookupList, Long id) {
    return lookupList.stream().filter(m -> m.getId().equals(id)).findFirst().get();
  }
}
