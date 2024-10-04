using UnityEngine;

public class AutoParry : MonoBehaviour
{
    public float parryForceMultiplier = 1.0f;

    private Rigidbody characterRigidbody;

    private void Start()
    {
        characterRigidbody = GetComponent<Rigidbody>();
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Ball"))
        {
            StartCoroutine(Parry(other.gameObject));
        }
    }

    private IEnumerator Parry(GameObject ball)
    {
        // Calculate time to reach
        float distance = Vector3.Distance(transform.position, ball.transform.position);
        float timeToReach = distance / ball.GetComponent<Rigidbody>().velocity.magnitude;

        yield return new WaitForSeconds(timeToReach);

        // Apply parry force
        Vector3 parryDirection = -ball.GetComponent<Rigidbody>().velocity.normalized;
        characterRigidbody.AddForce(parryDirection * ball.GetComponent<Rigidbody>().velocity.magnitude * parryForceMultiplier);
    }
}
