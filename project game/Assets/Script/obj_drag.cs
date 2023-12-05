using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class obj_drag : MonoBehaviour
{
    public Vector2 savedPosition;
    public bool IsDiatasObj;

    Transform SaveObj;

    public int id;

    void Start()
    {
        savedPosition = transform.localPosition; // Menggunakan localPosition daripada position
    }

    void Update()
    {
        // Tambahkan logika update jika diperlukan
    }

    public void OnMouseDown()
    {
        // Tambahkan logika onMouseDown jika diperlukan
    }

    public void OnMouseUp()
    {
        if (IsDiatasObj)
        {
            int id_tempat_drop = SaveObj.GetComponent<tempat_drop>().id;
            if(id == id_tempat_drop){
                transform.SetParent(SaveObj);
                transform.localPosition = Vector3.zero;
                transform.localScale = new Vector2(1.2f, 0.9f);
                SaveObj.GetComponent<SpriteRenderer>().enabled = false;
            }
            else{
                transform.localPosition = savedPosition;
            }
        }
        else
        {
            transform.localPosition = savedPosition;
        }
    }

    private void OnMouseDrag()
    {
        Vector2 pos = Camera.main.ScreenToWorldPoint(Input.mousePosition);
        transform.position = pos;
    }

    private void OnTriggerStay2D(Collider2D trig)
    {
        if (trig.gameObject.tag == "Drop")
        {
            IsDiatasObj = true;
            SaveObj = trig.gameObject.transform;
        }
    }

    private void OnTriggerExit2D(Collider2D trig)
    {
        if (trig.gameObject.tag == "Drop")
        {
            IsDiatasObj = false;
        }
    }
}
